package agile.dungeons

class GameMasterController {

    def characterService

    def index() {
        [
            username: session.username,            
            characters: characterService.list(),
            characterTypes: CharacterTypes.values(),
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

    def groupMessage(String message, String groupDescripion) {
        def charType = CharacterTypes.values().find({x -> x.description == groupDescripion})
        render "Querés decirle '${message}' al grupo de tipo '${charType.getValue()}'"
    }


}
