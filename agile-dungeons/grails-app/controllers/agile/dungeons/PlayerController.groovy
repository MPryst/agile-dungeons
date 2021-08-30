package agile.dungeons

class PlayerController {

    def characterService
    def playerService
    def index(String username) {
        def player = playerService.list().find({p -> p.username == username})
        def chars = characterService.list()        

        if (!player){
            response.status = 404
            render "Player not found"
        }
        [
            username: username,
            characterName: player.character.name,
            characters: chars,
        ]
    }

    def message(String message, String id) {
        render "Querés decirle '${message}' al character de id '${id}'"
    }

    def action(String actionMessage) {
        render "Querés pedirle '${actionMessage}' al DM"
    }
}
