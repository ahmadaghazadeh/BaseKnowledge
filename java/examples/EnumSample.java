public class EnumSample {
    

// Driver code
  public static void main(String args[])
    {
        // let's print name of each enum and there action
        // - Enum values() examples
        TrafficSignal[] signals = TrafficSignal.values();
  
        for (TrafficSignal signal : signals)
        {
            // use getter method to get the value
            System.out.println("name : " + signal.name() +
                        " action: " + signal.getAction() +
                        " index: " + signal.getIndex());
        }
    }


}
enum TrafficSignal
{
    // This will call enum constructor with one
    // String argument
    RED("STOP",1), GREEN("GO",2), ORANGE("SLOW DOWN",3);
  
    // declaring private variable for getting values
    private String action;
    private int index;
  
    // getter method
    public String getAction()
    {
        return this.action;
    }

    public int getIndex()
    {
        return this.index;
    }
  
    // enum constructor - cannot be public or protected
    private TrafficSignal(String action,int index)
    {
        this.action = action;
        this.index=index;
    }
}
  
