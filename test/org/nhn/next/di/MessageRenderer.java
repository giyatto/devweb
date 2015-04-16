package org.nhn.next.di;

public class MessageRenderer {
	
	private MessageProvider messageProvider;
	
	public void setMessageProvider(MessageProvider messageProvider) {
		this.messageProvider = messageProvider;
	}
	
	public void render(){
		System.out.println(messageProvider.getMessage());
	}
	
	public static void main(String[] args){
		MessageRenderer render = new MessageRenderer();
		
		render.setMessageProvider(new HelloWorldMessageProvider());
		render.render();
		
		render.setMessageProvider(new HiWorldMessageProvider());
		render.render();
	}
}
