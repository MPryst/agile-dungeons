package agile.dungeons

class Character {

    static constraints = {
    }
    
    String name
    Boolean awake
    String size
    String vision
    Integer perception      
}

enum CharacterTypes {
    MEDIUM(0, "Medium Size"),
    SMALL(1,  "Small Size"),
    DARKVISION(2, "Dark Vision"),
    REGULARVISION(3, "Regular Vision")

    private final int value
    private final String description

    CharacterTypes(int _value, String _description){
        this.value = _value
        this.description = _description
    }    
    
    int getValue(){ value }     

    String getDescription(){ description}      

    String toString(){ description}
}