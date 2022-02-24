package agile.dungeons

import grails.gorm.transactions.Transactional

@Transactional
class PlayerMessagingService {    
    def messageService
    def characterService

    def message(Long sourceCharacterId, Long destinationCharacterId, String message) { 

        def newMessage = new Message(
            characterService.list().find({c -> c.id == sourceCharacterId}), 
            characterService.list().find({c -> c.id == destinationCharacterId}),             
            message, 
            getPreviousMessage(sourceCharacterId))

        messageService.save(newMessage)
    }

    def askGM(Long sourceCharacterId, String message) {  
        def newMessage = new Message(
            characterService.list().find({c -> c.id == sourceCharacterId}), 
            null, 
            message,
            getPreviousMessage(sourceCharacterId))

        messageService.save(newMessage)
    }    

    def getPreviousMessage(Long sourceCharacterId) {
        def latests = messageService.list().findAll({m -> m.emisor != null && m.emisor.id == sourceCharacterId}).sort{it.date}            
        latests.reverse(true)

        if (latests.any{it -> it})
            return latests[0]        
        
        return null
    }
}
