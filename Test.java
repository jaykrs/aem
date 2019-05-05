import info.magnolia.context.MgnlContext;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import info.magnolia.jcr.util.NodeUtil; 
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;


public class Test {
    public static void main(String[] args) {
         print("value"); Session session = null;
		try {
            session = MgnlContext.getJCRSession("wave3-config");
            Node node = session.getNode("/en");
        walkNode(node);
        
        //   NodeUtil.copyInSession(node, "/en/GB/key6");
        //   Node n1 = session.getNode("/en/GB/key6");
        //    n1.setProperty("jcrName", "Key6");
        //     n1.setProperty("jcrValue", "Value666");
           session.save();
     //       print(n1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
            session.close();
                
            }catch (Exception e){}
            
        }
    }
    
    public static void walkNode( Node node ) throws RepositoryException {

  if(node.hasProperties()) {
	  updateNodeProperties(node);
  }
  if(!node.hasNodes()) return;
  
  else {
	  NodeIterator _nodeIterator = node.getNodes();
	  while(_nodeIterator.hasNext()) {
		  Node _node = _nodeIterator.nextNode();
		  walkNode(_node);
	  }
	  
  }  	
  }
  
  public static void updateNodeProperties(Node node) throws RepositoryException {
  
    	PropertyIterator _propIterator = node.getProperties();
    	while(_propIterator.hasNext()) {
    		Property p = _propIterator.nextProperty();
    		if(p.getString().contains("Generic-Site")) {
    			String _value = p.getValue().getString().replaceAll("Generic-Site", "");
    			node.setProperty(p.getName(), _value);
    		}
    	}
    		
    }
}
