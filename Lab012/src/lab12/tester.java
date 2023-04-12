package lab12;

public class tester {
	public static void main(String[] args) {
		Pacman maze1 = new Pacman("mazes/randomMaze.txt","output.txt");
		maze1.writeOutput();
		System.out.println(maze1);
	}
}
