package agile.dungeons

class Character {

    static constraints = {
    }
    
    String name
    Boolean awake
    String size
    String vision
    Integer perception
    Integer currentHitPoints
    Integer maximumHitPoints

    Character(        
        String name,
         String size, 
         String vision,
         Boolean awake,                   
         Integer perception, 
         Integer maximumHitPoints,
         Integer currentHitPoints) {
        
        this.name = name
        this.awake = awake
        this.size = size
        this.vision = vision
        this.perception = perception
        this.currentHitPoints = currentHitPoints
        this.maximumHitPoints = maximumHitPoints
    }

    void setAwake(Boolean awake) {
        if (this.awake == awake && awake == true) 
            throw new Exception("El personaje ya esta despierto")
        
        if (this.awake == awake && awake == false) 
            throw new Exception("El personaje ya esta inconsciente")

        if (awake == false)
            this.currentHitPoints = 0

        if (awake == true && this.currentHitPoints == 0)
            this.currentHitPoints = 1

        this.awake = awake
    }

    void setCurrentHitPoints(Integer currentHitPoints) {
        if (this.currentHitPoints == this.maximumHitPoints)
            throw new Exception("El personaje no necesita curarse!")

        if (currentHitPoints > this.maximumHitPoints)
            throw new Exception("No se puede curar a un personaje por encima de su salud maxima")
        
        if (this.currentHitPoints == 0 && !this.awake)
            throw new Exception("Se debe estabilizar a un personaje antes de curarlo")

        this.currentHitPoints = currentHitPoints
    }
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