package agile.dungeons

class PlayerController {

    def characterService
    def playerService
    def messageService

    def player
    def messages
    def index(String username) {
        player = playerService.list().find({p -> p.username == username})
        def chars = characterService.list()        
        messages = messageService.list().findAll({m -> m.emisor?.id == player.id || (m.receptor?.id == player.id && m.approved )})
        if (!player){
            response.status = 404
            render "Player not found"
        }
        [
            username: username,
            characterName: player.character.name,
            characters: chars,
            messages: messages
        ]
    }

    def message(String message, String id) {
        render "Querés decirle '${message}' al character de id '${id}'"
    }

    def action(String actionMessage) {
        render "Querés pedirle '${actionMessage}' al DM"
    }
}
