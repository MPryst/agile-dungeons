package agile.dungeons

import grails.gorm.transactions.Transactional

@Transactional
class GameMasterService {

    def messageService

    def sendMessage(Character receptor, String message) {
        if (!message || !receptor){
            throw new Exception("No se puede enviar el mensae. Datos invalidos.")
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
}
