package agile.dungeons

class GameMasterController {

    def characterService

    def index() {
        [
            username: session.username,            
            characters: characterService.list(),
        ]

     }

    def accept (String idMessage) {
        render "accept ${idMessage}"
     }

    def cancel (String idMessage) {
        render "cancel ${idMessage}"
    }

    def message(String message, String id) {
        render "Querés decirle '${message}' al character de id '${id}'"
    }

    def groupMessage(String message, String id) {
        render "Querés decirle '${message}' al character de id '${id}'"
    }


}
