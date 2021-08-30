package agile.dungeons

class PlayerController {

    def characterService

    def index(String username) {        
        render "Personajes ${characterService.list(name: username).name} - Usuario logueado: ${session.username}"
    }
}
