package agile.dungeons

class PlayerController {

    def characterService
    def playerService
    def index(String username) {
        def player = playerService.list().find({p -> p.username == username})
        if (!player){
            response.status = 404
            render "Player not found"
        }
        [
            username: username,
            characterName: player.character.name
        ]
    }
}
