package code.node;

public class PerformAction {
    public static Node perform(Node node,String actionType){
        switch (actionType){
            case "up": return node.up();
            case "down": return node.down();
            case "right": return node.right();
            case "left": return node.left();
            case "pickup": return node.pickup();
            case "drop" :return node.drop();
            default: return node.retrieve();
        }
    }
}
