package agile.dungeons

class PlayerController {

    def characterService
    def playerService
    def index(String username) {
        if (!playerService.list().find({p -> p.username == username})){
            response.status = 404
            render "Player not found"
        }
    }
}
