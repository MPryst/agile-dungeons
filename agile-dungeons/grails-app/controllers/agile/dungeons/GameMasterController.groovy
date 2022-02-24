package agile.dungeons

class GameMasterController {

    def characterService
    def messageService
    def gameMasterService

    def message
    def sentMessages
    def index(Boolean created) {
        message = messageService.list(sort:"date").find({m -> m.approved == null})
        sentMessages = messageService.list().findAll({m -> m.approved != null}).reverse(true)
    
        [
            created: created,
            username: session.username,            
            characters: characterService.list(),
            charactersUnconscious: characterService.list().findAll({c -> !c.awake}),
            charactersAwake: characterService.list().findAll({c -> c.awake}),
            characterTypes: CharacterTypes.values(),
            message: !message ? null : [
                emisor: message.emisor ? message.emisor.name : "GM",
                receptor: message.receptor ? message.receptor.name : "GM",
                content: message.content,
                approved: message.approved == null ? "Pendiente..." : message.approved ? "Aprobado" : "Rechazado",
            ],
            sentMessages: sentMessages.collect({m -> [                                
                emisor: m.emisor ? m.emisor.name : "GM",
                receptor: m.receptor ? m.receptor.name : "GM",
                content: m.content,
                approved: m.approved == null ? "Pendiente..." : m.approved ? "Aprobado" : "Rechazado",
                color: m.approved == null ? "orange" : m.approved ? "green" : "red",
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
        def receptorCharacter = characterService.get(id)
        try {            
            gameMasterService.sendMessage(receptorCharacter, message)        
            redirect(controller: "GameMaster", action: "index", params: [created: true])        
        }catch (Exception e) {
            response.status = 400
            render "${e.message}"
        }
    }

    def awake(Long idCharacter){
        def character = characterService.get(idCharacter)        
        try {
            gameMasterService.sendMessage(character, "Te despertaste!")        
            gameMasterService.awake(character)
            redirect(controller: "GameMaster", action: "index")
        } catch (Exception e) {
            response.status = 400
            render "${e.message}"
        }
    }

    def sleep(Long idCharacter){
        def character = characterService.get(idCharacter)
        try {
            gameMasterService.sendMessage(character, "Estas inconsciente")        
            gameMasterService.sleep(character)
            redirect(controller: "GameMaster", action: "index")
        } catch (Exception e) {
            response.status = 400
            render "${e.message}"
        }
    }

    def heal(Long idCharacter){
        def character = characterService.get(idCharacter)
        try {
            gameMasterService.sendMessage(character, "Se restablecieron tus Hit Points")        
            gameMasterService.heal(character)
            redirect(controller: "GameMaster", action: "index")
        } catch (Exception e) {
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