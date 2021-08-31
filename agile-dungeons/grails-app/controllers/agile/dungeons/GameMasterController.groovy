package agile.dungeons

class GameMasterController {

    def characterService
    def messageService

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
            message: [
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

    def accept (String idMessage) {
        render "accept ${idMessage}"
     }

    def cancel (String idMessage) {
        render "cancel ${idMessage}"
    }

    def message(String message, String id) {
        def receptorCharacter = characterService.get(id)
        if (!message || !receptorCharacter){
            response.status = 400
            render "Bad request"
        }

        def newMessage = new Message(
            date: new Date(),
            emisor: null,
            receptor: receptorCharacter,
            approved: true,
            content: message
        )
        messageService.save(newMessage)
        redirect(controller: "GameMaster", action: "index", params: [created: true])        
    }

    def groupMessage(String message, String groupDescripion) {
        def charType = CharacterTypes.values().find({x -> x.description == groupDescripion})
        render "Querés decirle '${message}' al grupo de tipo '${charType.getValue()}'"
    }

}