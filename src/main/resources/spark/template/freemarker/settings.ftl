<#-- @ftlvariable name="image" type="java.lang.String" -->
<#-- @ftlvariable name="notifications" type="java.util.List<Notification>" -->
<#import "userMasterTemplate.ftl" as layout />
<@layout.userMasterTemplate title="Settings">

    <div class="container">
        <div class="row d-flex align-items-center justify-content-center">
            <div class="col-12 d-flex align-items-center justify-content-center my-5" id="selectedImage">
                <img src="data:image/jpeg;base64,${image}" alt="User" style="border-radius:50%; width:250px; height:250px;">
            </div>
            <div class="col-12 w-75">
                <form action="/settings" role="form" method="post" enctype="multipart/form-data">
                    <div class="input-group " style="font-family: 'LEMON MILK'; margin-bottom: 0.5rem">
                        <!--<label for="img" class="form-label" style="color: white;">Choose new profile picture</label>-->
                        <input type="file" class="form-control" id="img" accept="image/jpg, image/png" name="image" placeholder="Choose new profile picture"/>
                        <button type="submit" class="btn btn-profile" style="font-family: 'LEMON MILK'; color:white;">Confirm</button>
                    </div>
                    <div id="selectedBanner" class="d-flex justify-content-center">

                    </div>
                    <br>
                    <div>

                    </div>
                </form>
            </div>
        </div>
    </div>

</@layout.userMasterTemplate>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
    var selDiv = "";
    var storedFiles = [];
    $(document).ready(function () {
        $("#img").on("change", handleFileSelect);
        selDiv = $("#selectedImage");
    });
    function handleFileSelect(e) {
        var files = e.target.files;
        var filesArr = Array.prototype.slice.call(files);
        filesArr.forEach(function (f) {
            if (!f.type.match("image.*")) {
                return;
            }
            storedFiles.push(f);
            var reader = new FileReader();
            reader.onload = function (e) {
                var html = "<img src=\"" + e.target.result + "\" data-file='" + f.name + "' alt='Could not show image' style='height: 250px; width: 250px; border-radius: 50%'>";
                selDiv.html(html);
            };
            reader.readAsDataURL(f);
        });
    }
</script>