package agile.dungeons

class LoginController {

    def index() {
    }    

    def login(String username, String password) {
        if (username == "GameMaster" && password == "123456") {
            session.rol = "GM"
            session.username = "GameMaster"            
            redirect(controller: "gameMaster", action: "index")
            
        } else {
            session.rol = "Player"
            session.username = "${username}"            
            redirect(controller: "player", action: "index", params: [username: username])
        }
     }
}