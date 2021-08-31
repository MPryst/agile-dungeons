package agile.dungeons

import grails.gorm.transactions.Transactional

@Transactional
class PlayerMessagingService {
    def messageService
    def characterService

    def message(Long sourceCharacterId, Long destinationCharacterId, String message) { 
        def sourceCharacter = characterService.list().find({c -> c.id == sourceCharacterId})
        def destinationCharacter = characterService.list().find({c -> c.id == destinationCharacterId})
        if (!sourceCharacter.awake){
            throw new Exception("No se pueden enviar mensajes estando inconsciente.")
        }

        if (!destinationCharacter.awake){
            throw new Exception("Un personaje inconsciente no puede recibir mensajes.")
        }

        if (messageService.list().find({m -> m.approved == null && m.emisor != null && m.emisor.id == sourceCharacterId})) {
            throw new Exception("No se puede enviar un mensaje nuevo mientras tengas alguno pendiente...")
        }        
        def latests = messageService.list().findAll({m -> m.emisor != null && m.emisor.id == sourceCharacterId}).sort{it.date}
        latests.reverse(true)        
        
        if (latests.any{it -> it}){
            def latestSent = latests[0].date
            def duration = groovy.time.TimeCategory.minus(
                new Date(),
                latestSent
            );
            //if (duration.days == 0 && duration.hours ==0 && duration.minutes < 10){
            if (duration.days == 0 && duration.hours ==0 && duration.minutes == 0 && duration.seconds < 40){
                println "Latest: ${latests[0].content} on ${duration.days}d ${duration.hours}h ${duration.minutes}m duration ago"
                throw new Exception("Pasaron ${duration.hours}:${duration.minutes}:${duration.seconds} desde tu último mensaje. Sólo se puede enviar un mensaje cada 10 minutos.")                
            }
        }       

        def newMessage = new Message(
            date: new Date(),
            emisor: sourceCharacter,
            receptor: destinationCharacter,
            approved: null,
            content: message)
        
        messageService.save(newMessage)

    }

    def askGM(Long sourceCharacterId, String message) {  
        def sourceCharacter = characterService.list().find({c -> c.id == sourceCharacterId})        
        if (!sourceCharacter.awake){
            throw new Exception("No se pueden enviar mensajes estando inconsciente.")
        }        

        if (messageService.list().find({m -> m.approved == null && m.emisor != null && m.emisor.id == sourceCharacterId})) {
            throw new Exception("No se puede enviar un mensaje nuevo mientras tengas alguno pendiente...")
        }        
        def latests = messageService.list().findAll({m -> m.emisor != null && m.emisor.id == sourceCharacterId}).sort{it.date}
        latests.reverse(true)        
        
        if (latests.any{it -> it}){
            def latestSent = latests[0].date
            def duration = groovy.time.TimeCategory.minus(
                new Date(),
                latestSent
            );
            //if (duration.days == 0 && duration.hours ==0 && duration.minutes < 10){
            if (duration.days == 0 && duration.hours ==0 && duration.minutes == 0 && duration.seconds < 40){
                println "Latest: ${latests[0].content} on ${duration.days}d ${duration.hours}h ${duration.minutes}m duration ago"
                throw new Exception("Pasaron ${duration.hours}:${duration.minutes}:${duration.seconds} desde tu último mensaje. Sólo se puede enviar un mensaje cada 10 minutos.")                
            }
        }       

        def newMessage = new Message(
            date: new Date(),
            emisor: sourceCharacter,
            receptor: null,
            approved: null,
            content: message)
        
        messageService.save(newMessage)
    }
}
