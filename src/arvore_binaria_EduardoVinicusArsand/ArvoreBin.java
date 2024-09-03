package arvore_binaria_EduardoVinicusArsand;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ArvoreBin {
	private Nodo raiz;

	public ArvoreBin() {
		raiz = null;
	}

	public Nodo alocarNodo(int valor) {
		Nodo novoNodo = new Nodo();
		novoNodo.info = valor;
		novoNodo.esq = novoNodo.dir = novoNodo.pai = null;
		return novoNodo;
	}

	public void inserir(int valor) {
		raiz = inserir(valor, raiz);
	}

	private Nodo inserir(int valor, Nodo raiz) {
		if (raiz == null) {
			raiz = alocarNodo(valor);
		} else {
			if (valor < raiz.info) {
				raiz.esq = inserir(valor, raiz.esq);
				//raiz.esq.pai = raiz;
			}

			if (valor > raiz.info) {
				raiz.dir = inserir(valor, raiz.dir);
				//raiz.dir.pai = raiz;
			}
		}
		return raiz;
	}

	public void preOrdem() {
		preOrdem(raiz);
	}

	private void preOrdem(Nodo raiz) {
		if (raiz != null) {
			System.out.print(raiz.info + " ");
			preOrdem(raiz.esq);
			preOrdem(raiz.dir);
		}
	}

	public void central() {
		central(raiz);
	}

	private void central(Nodo raiz) {
		if (raiz != null) {
			central(raiz.esq);
			System.out.print(raiz.info + " ");
			central(raiz.dir);
		}
	}

	public void posOrdem() {
		posOrdem(raiz);
	}

	private void posOrdem(Nodo raiz) {
		if (raiz != null) {
			posOrdem(raiz.esq);
			posOrdem(raiz.dir);
			System.out.print(raiz.info + " ");
		}
	}

	public void remover(int valor) {
		raiz = remover(valor, raiz);
	}

	private Nodo remover(int valor, Nodo raiz) {
		if (raiz == null) {
			return null;
		}

		if (valor < raiz.info) {
			raiz.esq = remover(valor, raiz.esq);
		} 
		else if (valor > raiz.info) {
			raiz.dir = remover(valor, raiz.dir);
		} 
		else {
			
			if (raiz.esq == null) {
				return raiz.dir;
			} else if (raiz.dir == null) {
				return raiz.esq;
			}
			
			raiz.info = buscarMin(raiz.dir).info; 
			raiz.dir = remover(raiz.info, raiz.dir); 
		}
		return raiz;
	}

	public Nodo buscar(int valor) {
		return buscar(valor, raiz);
	}

	private Nodo buscar(int valor, Nodo raiz) {
		if (raiz.info == valor || raiz == null) {
			return raiz;
		}

		if (raiz.info < valor) {
			return buscar(valor, raiz.dir);
		}

		return buscar(valor, raiz.esq);

	}

	public Nodo buscarMin() {
		return buscarMin(raiz);
	}

	private Nodo buscarMin(Nodo raiz) {
		if (raiz == null) {
			return null;
		}

		if (raiz.esq == null) {
			return raiz;
		}

		return buscarMin(raiz.esq);

	}

	public void gerarArqDot(String filename) {
		try {
			FileWriter fileWriter = new FileWriter(filename);
			BufferedWriter out = new BufferedWriter(fileWriter);
			out.write("digraph ArvoreBin {\n");
			escreverPreOrdemDot(raiz, out);
			out.write("}\n");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void escreverPreOrdemDot(Nodo raiz, BufferedWriter out) throws IOException {
		if (raiz != null) {
			out.write("\t" + raiz.info + ";\n");
			if (raiz.esq != null) {
				out.write("\t" + raiz.info + " -> " + raiz.esq.info + ";\n");
			}
			if (raiz.dir != null) {
				out.write("\t" + raiz.info + " -> " + raiz.dir.info + ";\n");
			}
			escreverPreOrdemDot(raiz.esq, out);
			escreverPreOrdemDot(raiz.dir, out);
		}
	}
}
