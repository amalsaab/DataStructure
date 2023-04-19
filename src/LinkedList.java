
public class LinkedList<T> implements List<T> {
	private Node<T> head,current;
	
	public LinkedList() {
		head=current=null;
	}
	@Override
	public boolean empty() {
		return head ==null;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public void findFirst() {
		current=head;

	}

	@Override
	public void findNext() {
		current=current.next;

	}

	@Override
	public boolean last() {
		return current.next==null;
	}

	@Override
	public T retrieve() {
		return current.data;
	}

	@Override
	public void update(T e) {
		current.data=e;

	}

	@Override
	public void insert(T e) {
		Node<T> p=new Node<T>(e);
		if(head==null) head=current=p;
		else {
			p.next=current.next;
			current.next=p;
			current=current.next;
		}
	}

	@Override
	public void remove() {
		if (head==current) {
			head=head.next;
		}else {
			Node<T> q=head;
			while(q.next!=current)
				q=q.next;
			q.next = current.next;				
		}
		
		if(current.next==null)
			current=head;
		else
			current=current.next;			

	}

}

