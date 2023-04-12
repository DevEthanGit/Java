package lab02;

public class Matrix {
	int numRows;
	int numColumns;
	int[][] data;
	
	public Matrix(){}

	public Matrix(int rowDim, int colDim){
		this.numRows = rowDim;
		this.numColumns = colDim;
		this.data = new int[numRows][numColumns];
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				data[i][j] = 0;
			}
		}

	}
	
	

	public Matrix(int d[][])
	{
		this.numRows = d.length;
		if(numRows > 0){
			this.numColumns = d[0].length;
		}
		else
			this.numColumns = 0;
		this.data = new int[numRows][numColumns];
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				this.data[i][j] = d[i][j];
			}
		}
	}	
	
	@Override
	public String toString() {
		String m = "\n";
		char s = ' ';
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				m+=data[i][j]+" ";
			}
			m+="\n";
		}
		return m;
	}
	

	@Override
	public boolean equals(Object o)
	{
		Matrix n = this;
		boolean tf = false;
		if(!(o instanceof Matrix))
			return false;
		Matrix m = (Matrix)o;
		if(n.data.length == m.numRows){
			if(n.data[0].length == m.numColumns){
				for (int i = 0; i < n.numRows; i++) {
					for (int j = 0; j < n.numColumns; j++) {
						if(n.data[i][j] != m.data[i][j]){
							tf = false;
						}
						else{
							tf = true;
						}
					}
				}
			}
		}


		return tf; // placeholder
	}

	public Matrix mult(Matrix m)
	{
		Matrix a = this;
		if(a.numColumns!=m.numRows){
			return null;
		}
		int[][] n = new int[a.numRows][m.numColumns];
		int o;
		for(int i = 0; i < a.numRows;i++){
			for(int j = 0; j<m.numColumns;j++){
				o=0;
				for(int k = 0; k<a.numColumns;k++){
					o += a.data[i][k]*m.data[k][j];
					n[i][j] = o;
				}
			}
		}
		Matrix multiplacation = new Matrix(n);
 		return multiplacation;

	}
	
	public Matrix add(Matrix m)
	{
		Matrix a = this;
		if((a.numRows!=m.numRows) || (a.numColumns!=m.numColumns)){
			return null;
		}
		Matrix n = new Matrix(a.numRows,a.numColumns);
		for(int i = 0; i<a.numRows;i++){
			for(int j = 0; j<a.numColumns;j++){
				n.data[i][j] = a.data[i][j]+m.data[i][j];
			}
		}

		return n;
	}
    
    public Matrix transpose()
    {
		Matrix a = this;
		for (int i = 0; i < numRows; i++) {
			for (int j = i + 1; j < numRows; j++) {
				int temp = a.data[i][j];
				a.data[i][j] = a.data[j][i];
				a.data[j][i] = temp;
			}
		}
        return a;
    }

}
