package uca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import uca.core.dominio.Autocaravana;
import uca.core.dominio.Cliente;
import uca.core.dominio.Reserva;

@Component
public class NotificacionEmail {

    private MailSender mailSender;

    @Autowired
    public NotificacionEmail(MailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void enviarReservaRealizadaNotifiacion(Reserva reserva, Cliente cliente, Autocaravana autocaravana) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String destinatario = cliente.getEmail();
        String asunto = "Reserva realizada";
        String mensaje = "Se ha realizado la reserva de la autocaravana " + autocaravana.getModelo() + " con matricula " + autocaravana.getMatricula() + " para el cliente " + cliente.getNombre() + " " + cliente.getApellido() + " con DNI " + cliente.getDni() + " desde el " + reserva.getFechaIni() + " hasta el " + reserva.getFechaFin();
        mailMessage.setFrom("maria251@afpeterg.com");
        mailMessage.setSubject(asunto);
        mailMessage.setText(mensaje);
        mailMessage.setTo(destinatario);
        try {
            mailSender.send(mailMessage);
        } catch (Exception e) {
            System.out.println("Error al enviar el email: " + e.getMessage());
        }
    }
}
