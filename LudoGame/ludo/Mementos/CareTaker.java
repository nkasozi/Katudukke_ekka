package Mementos;

import java.util.Stack;

public class CareTaker
{
	
	// private static final int MAX_NUM_OF_MEMENTOS = 4;
	private static final Stack<Memento>	memento_stack	= new Stack<Memento>();
	
	public static void AddMemento(Memento memento)
	{
		memento_stack.push(memento);
	}
	
	public static Memento GetMostRecentMemento()
	{
		try
		{
			return memento_stack.pop();
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	public static Memento GetCurrentState()
	{
		try
		{
			return memento_stack.firstElement();
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public static void ClearMemoryStack()
	{
		memento_stack.clear();
	}
}
