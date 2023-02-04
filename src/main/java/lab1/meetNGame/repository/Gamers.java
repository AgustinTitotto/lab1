package lab1.meetNGame.repository;


import lab1.meetNGame.model.GamerUser;
import lab1.meetNGame.UI.SignUpForm;
import lab1.meetNGame.persistence.EntityTransactions;

import javax.imageio.ImageIO;
import javax.management.Query;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import static lab1.meetNGame.persistence.EntityManagers.currentEntityManager;
import static lab1.meetNGame.persistence.EntityTransactions.tx;

public class Gamers{

    public boolean exists(String userName) {
        return findByUserName(userName).isPresent();
    }

    public Optional<GamerUser> findByUserName(String userName) {
        return tx(() -> currentEntityManager()
                .createQuery("SELECT u FROM GamerUser u WHERE u.userName LIKE :userName", GamerUser.class)
                .setParameter("userName", userName).getResultList()).stream()
                .findFirst();
    }

    public GamerUser createGamer(SignUpForm form) throws IOException {
        BufferedImage bImage = ImageIO.read(new File("./src/main/resources/public/img/DefaultImage.png"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", bos);
        byte[] data = bos.toByteArray();

        final GamerUser newGamer = GamerUser.create(form.getUserName(), form.getPassword(), false, new String(Base64.getEncoder().encode(data)));

        if (exists(newGamer.getUserName())) throw new IllegalStateException("UserName already exists.");

        return EntityTransactions.persist(newGamer);
    }

    public void updateByProfilePicture(GamerUser gamerUser, String image) {
        tx(() -> currentEntityManager().createQuery("UPDATE GamerUser u SET u.image = ?1 WHERE u.userName LIKE:userName")
                .setParameter("userName", gamerUser.getUserName()).setParameter(1, image).executeUpdate());
    }

    public String getProfilePicture(GamerUser gamerUser) {
        return tx(() -> currentEntityManager().createQuery("SELECT u FROM GamerUser u WHERE u.userName LIKE:userName", GamerUser.class)
                .setParameter("userName", gamerUser.getUserName()).getResultList()).stream().findFirst().get().getImage();
    }
}
