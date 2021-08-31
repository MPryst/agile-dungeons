package agile.dungeons

import grails.gorm.transactions.Transactional

@Transactional
class PlayerMessagingService {
    final SECONDS_BEFORE_ANOTHER_MESSAGE = 40
    def messageService
    def characterService

    def message(Long sourceCharacterId, Long destinationCharacterId, String message) { 
        def sourceCharacter = characterService.list().find({c -> c.id == sourceCharacterId})
        def destinationCharacter = characterService.list().find({c -> c.id == destinationCharacterId})

        if (sourceCharacterId == destinationCharacterId){
            throw new Exception("No te puedes mandar un mensaje a ti mismo.")
        }

        if (!sourceCharacter.awake){
            throw new Exception("No se pueden enviar mensajes estando inconsciente.")
        }

        if (!destinationCharacter.awake){
            throw new Exception("Un personaje inconsciente no puede recibir mensajes.")
        }

        if (messageService.list().find({m -> m.approved == null && m.emisor != null && m.emisor.id == sourceCharacterId})) {
            throw new Exception("No se puede enviar un mensaje nuevo mientras tengas alguno pendiente...")
        }    
        validateMessage(sourceCharacterId, message)            

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

        validateMessage(sourceCharacterId, message)        

        def newMessage = new Message(
            date: new Date(),
            emisor: sourceCharacter,
            receptor: null,
            approved: null,
            content: message)
        
        messageService.save(newMessage)
    }

    def validateMessage(Long sourceCharacterId, String message){
        if (!message){
            throw new Exception("El mensaje no puede ser vacío");
        }
        def latests = messageService.list().findAll({m -> m.emisor != null && m.emisor.id == sourceCharacterId}).sort{it.date}
        latests.reverse(true)        
        
        if (latests.any{it -> it}){
            def latestSent = latests[0].date
            def duration = groovy.time.TimeCategory.minus(
                new Date(),
                latestSent
            );

            if (latests[0].content == message && !latests[0].approved){
                throw new Exception("No se puede enviar consecutivamente el mensaje rechazado: ${message}");
            }
            
            if (duration.days == 0 && duration.hours ==0 && duration.minutes == 0 && duration.seconds < SECONDS_BEFORE_ANOTHER_MESSAGE){
                throw new Exception("Todavía no estás habilitado para enviar otro mensaje. Debes esperar.")
                println "There's only been: ${duration.days}d ${duration.hours}h ${duration.minutes}m ${duration.seconds}s after the last message."                
            }
        }         
    }
}
