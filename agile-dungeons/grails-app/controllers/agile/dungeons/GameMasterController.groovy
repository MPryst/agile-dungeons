package agile.dungeons

class GameMasterController {

    def characterService
    def messageService
    def gameMasterService

    def message
    def sentMessages
    def index(Boolean created) {
        message = messageService.list(sort:"date").find({m -> m.approved == null})
        sentMessages = messageService.list().findAll({m -> m.emisor == null})
    
        [
            created: created,
            username: session.username,            
            characters: characterService.list(),
            characterTypes: CharacterTypes.values(),
            message: !message ? null : [
                emisor: message.emisor ? message.emisor.name : "GM",
                receptor: message.receptor ? message.receptor.name : "GM",
                content: message.content,
                approved: message.approved == null ? "Pendiente..." : message.approved ? "Aprobado" : "Rechazado",
            ],
            sentMessages: sentMessages.collect({m -> [                                
                receptor: m.receptor?.name,
                content: m.content,
             ]
            }),
        ]

     }

    def accept () {        
        gameMasterService.approve(message)
        redirect(controller: "GameMaster", action: "index")
     }

    def cancel () {
        gameMasterService.reject(message)
        redirect(controller: "GameMaster", action: "index")
    }    

    def message(String message, String id) {        
        try {
            def receptorCharacter = characterService.get(id)
            gameMasterService.sendMessage(receptorCharacter, message)        
            redirect(controller: "GameMaster", action: "index", params: [created: true])        
        }catch (Exception e) {
            response.status = 400
            render "${e.message}"
        }
    }

    def groupMessage(String message, String groupDescripion) {
        try {
            def charType = CharacterTypes.values().find({x -> x.description == groupDescripion})
            gameMasterService.sendMessage(charType, message)
            redirect(controller: "GameMaster", action: "index", params: [created: true])
        }catch (Exception e) {
            response.status = 400
            render "${e.message}"
        }
    }
}