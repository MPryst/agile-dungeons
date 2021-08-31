package agile.dungeons

class Message {

    static constraints = {
        emisor nullable: true
        receptor nullable: true
        approved nullable: true
        content blank:false, nullable: false
    }

    Date date
    Character emisor
    Character receptor
    Boolean approved
    String content
}
