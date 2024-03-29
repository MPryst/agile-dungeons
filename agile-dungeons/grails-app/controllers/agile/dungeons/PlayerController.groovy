package agile.dungeons

class PlayerController {

    def characterService
    def playerService
    def messageService
    def playerMessagingService

    def player
    def messagesList
    def index(String username) {
        player = playerService.list().find({p -> p.username == username})
        if (!player){
            response.status = 404
            render "Jugador ${username} no encontrado"
        }
        
        def chars = characterService.list().findAll({p -> p.id != player.character.id})        
        messagesList = messageService.list().findAll({m -> m.emisor != null && m.emisor?.id == player.character.id || (m.receptor?.id == player.character.id && m.approved )}).reverse(true)
        [
            maximumHitPoints: player.character.maximumHitPoints,
            currentHitPoints: player.character.currentHitPoints,
            username: username,
            characterName: player.character.name,
            awake: player.character.awake,
            characters: chars,
            messages: messagesList.collect({m -> [
                id: m.id,
                emisor: m.emisor ? m.emisor?.name : "GM",
                receptor: m.receptor ? m.receptor?.name : "GM",
                approved: m.approved == null ? "Pendiente..." : m.approved ? "Aprobado" : "Rechazado",
                color: m.approved == null ? "orange" : m.approved ? "green" : "red",
                content: m.content,                
             ]
            }),
        ]
    }

    def message(String message, String id) {
        try {
            playerMessagingService.message(player.character.id, id as Long, message)
            redirect(controller: "Player", action: "index", params: [username: player.username])
        } catch (Exception e) {
            response.status = 400
            render "${e.message}"
        }
    }

    def action(String actionMessage) {
        try {
            playerMessagingService.askGM(player.character.id, actionMessage)
            redirect(controller: "Player", action: "index", params: [username: player.username])
        } catch (Exception e) {
            response.status = 400
            render "${e.message}"
        }
        
    }
}
