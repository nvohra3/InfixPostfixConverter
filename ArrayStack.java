import java.util.Arrays;
/**
 * Allows user to create array stack of type T
 * @author Neil Vohra
 * @param <T> type of array stack
 */
public class ArrayStack<T> implements StackADT<T> {

	private final static int DEFAULT_CAPACITY = 20;
	public int top;
	private T[] stack;

	/**
	 * Creates an empty array stack with default capacity
	 */
	@SuppressWarnings("unchecked")
	public ArrayStack() {
		top = -1;
		stack = (T[]) (new Object[DEFAULT_CAPACITY]);
	}

	/**
	 * Creates an empty array stack using the specified capacity.
	 * @param initialCapacity the initial size of the array
	 */
	@SuppressWarnings("unchecked")
	public ArrayStack(int initialCapacity) {
		top = -1;
		stack = (T[]) (new Object[initialCapacity]);
	}

	@Override
	public void push(T element) {
		if (size() == stack.length)
			expandCapacity();
		top++;
		stack[top] = element;
	}

	@Override
	public T pop() throws EmptyCollectionException {
		if (isEmpty())
			throw new EmptyCollectionException("array stack");
		T result = stack[top];
		stack[top] = null;
		top--;
		return result;
	}

	@Override
	public T peek() throws EmptyCollectionException {
		if (isEmpty())
			throw new EmptyCollectionException("array stack");
		return stack[top];
	}

	@Override
	public boolean isEmpty() {
		return top == -1;
	}

	private void expandCapacity() {
		stack = Arrays.copyOf(stack, stack.length * 2);
	}

	public String toString() {
		String result = "<top of stack>\n";
		for (int index = top; index >= 0; index--)
			result += stack[index] + "\n";
		return result + "<bottom of stack>";
	}

	@Override
	public int size() {
		return top + 1;
	}
}
