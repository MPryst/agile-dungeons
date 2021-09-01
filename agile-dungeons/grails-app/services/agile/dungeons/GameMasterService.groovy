package agile.dungeons

import grails.gorm.transactions.Transactional

@Transactional
class GameMasterService {

    def messageService
    def characterService

    def sendMessage(Character receptor, String message) {
        if (!message || !receptor){
            throw new Exception("No se puede enviar el mensaje. Datos inválidos.")
        }

        def newMessage = new Message(
            date: new Date(),
            emisor: null,
            receptor: receptor,
            approved: true,
            content: message
        )
        messageService.save(newMessage)
    }

    def sendMessage(CharacterTypes charType, String message) {
        if (!message || !charType){
            throw new Exception("No se puede enviar el mensaje. Datos inválidos.")
        }
        def charsInTheGroup
        switch(charType){
            case CharacterTypes.MEDIUM:
                charsInTheGroup = characterService.list().findAll({c -> c.size == "Medium" && c.awake})
                break;
            case CharacterTypes.SMALL:
                charsInTheGroup = characterService.list().findAll({c -> c.size == "Small" && c.awake})         
                break;
            case CharacterTypes.DARKVISION:
                charsInTheGroup = characterService.list().findAll({c -> c.vision == "Darkvision" && c.awake})
                break;
            case CharacterTypes.REGULARVISION:
                charsInTheGroup = characterService.list().findAll({c -> c.vision == "Regularvision" && c.awake})
                break;
            default :
                throw new Exception("Selección de personajes inválida.")
                brak;
        }

        charsInTheGroup.each {
            sendMessage(it, message)
        }        
    }

    def approve(Message message){
        message.approved = true
        messageService.save(message)
    }

    def reject(Message message){
        message.approved = false
        messageService.save(message)
    }
}
