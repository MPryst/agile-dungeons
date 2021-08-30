package agile.dungeons

class PlayerController {

    def index(String username) { 
        render "hola usuario: ${session.username} ${session.rol}"
    }
}
