package agile.dungeons

class Message {
    final SECONDS_BEFORE_ANOTHER_MESSAGE = 40

    static constraints = {
        emisor nullable: true
        receptor nullable: true
        approved nullable: true
        content blank:false, nullable: false
    }

    Message(Character emisor, Character receptor, String content, Message previousMessage) {
        def date = new Date()
        if (previousMessage) {
            def latestSent = previousMessage.date
            def duration = groovy.time.TimeCategory.minus(new Date(),latestSent);

            if (previousMessage.approved == null && previousMessage.emisor != null && previousMessage.emisor.id == emisor.id) 
                throw new Exception("No se puede enviar un mensaje nuevo mientras tengas alguno pendiente...")            

            if (previousMessage.content == content && !previousMessage.approved)
                throw new Exception("No se puede enviar consecutivamente el mensaje rechazado: ${content}");            
            
            if (duration.days == 0 && duration.hours == 0 && duration.minutes == 0 && duration.seconds < SECONDS_BEFORE_ANOTHER_MESSAGE)
                throw new Exception("Todavía no estás habilitado para enviar otro mensaje. Debes esperar.")            
        }

        if (!emisor.awake)
            throw new Exception("No se pueden enviar mensajes estando inconsciente.")        

        if (!content)
            throw new Exception("El mensaje no puede ser vacío");        

        if (receptor){
            if (emisor.id == receptor.id)
                throw new Exception("No te puedes mandar un mensaje a ti mismo.")            

            if (!receptor.awake)
                throw new Exception("Un personaje inconsciente no puede recibir mensajes.")            
        }        
        
        this.date = date
        this.emisor = emisor
        this.receptor = receptor
        this.approved = null
        this.content = content        
    }

    Message(Character receptor, String content) {
        if (!content || !receptor)
            throw new Exception("No se puede enviar el mensaje. Datos inválidos.")        

        this.date = new Date()
        this.emisor = null
        this.receptor = receptor        
        this.approved = true
        this.content = content
    }

    void setApproved(Boolean approved){
        if(this.approved != null) 
            throw new Exception("No se puede volver a cambiar el estado de un mensaje")        

        this.approved = approved        
    }


    Date date
    Character emisor
    Character receptor
    Boolean approved
    String content    
}
