<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
</head>
<body>
<#if message??>
    <div class="alert alert-success">
        ${message}
    </div>
</#if>

    <p>Create a new game description</p>
    <button>
        <a href="/createdescription" class="hpl">Create Description</a>
    </button>
    <p>View or delete your descriptions</p>
    <button>
        <a href="/managedescription" class="hpl">Manage Description</a>
    </button>

</body>
</html>