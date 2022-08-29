package br.com.improving.carrinho;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Classe que representa o carrinho de compras de um cliente.
 */
public class CarrinhoCompras {

	private ArrayList<Item> itensCarrinho = new ArrayList<Item>();

	public CarrinhoCompras(){
	}

    /**
     * Permite a adição de um novo item no carrinho de compras.
     *
     * Caso o item já exista no carrinho para este mesmo produto, as seguintes regras deverão ser seguidas:
     * - A quantidade do item deverá ser a soma da quantidade atual com a quantidade passada como parâmetro.
     * - Se o valor unitário informado for diferente do valor unitário atual do item, o novo valor unitário do item deverá ser
     * o passado como parâmetro.
     *
     * Devem ser lançadas subclasses de RuntimeException caso não seja possível adicionar o item ao carrinho de compras.
     *
     * @param produto
     * @param valorUnitario
     * @param quantidade
     */
	public void adicionarItem(Produto produto, BigDecimal valorUnitario, int quantidade) {
		if(produto == null){
			throw new RuntimeException("O objeto produto deve ser instanciado!");
		}else{
			Item itemProcurado = itemProdutoExisteEmCarrinho(produto);
			if(itemProcurado == null){
				Item novoItem = new Item(produto, valorUnitario, quantidade);
				itensCarrinho.add(novoItem);
			}else{
				itemProcurado.setQuantidade(itemProcurado.getQuantidade() + quantidade);
				itemProcurado.setValorUnitario(valorUnitario);
				removerItem(produto);
				itensCarrinho.add(itemProcurado);
			}
		}
	}

    /**
     * Permite a remoção do item que representa este produto do carrinho de compras.
     *
     * @param produto
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
	public boolean removerItem(Produto produto) {
		for (Item item : itensCarrinho) {
			if(item.getProduto().equals(produto)){
				return itensCarrinho.remove(item);
			}
		}
		return false;
	}

    /**
     * Permite a remoção do item de acordo com a posição.
     * Essa posição deve ser determinada pela ordem de inclusão do produto na 
     * coleção, em que zero representa o primeiro item.
     *
     * @param posicaoItem
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
	public boolean removerItem(int posicaoItem) {

		if(posicaoItem < itensCarrinho.size()){
			boolean remove = itensCarrinho.remove(itensCarrinho.get(posicaoItem));
			return remove;
		}

		return false;
	}

    /**
     * Retorna o valor total do carrinho de compras, que deve ser a soma dos valores totais
     * de todos os itens que compõem o carrinho.
     *
     * @return BigDecimal
     */
	public BigDecimal getValorTotal() {
		BigDecimal valorTotal = new BigDecimal("0.0");
		for (Item item : itensCarrinho){
			valorTotal.add(item.getValorTotal());
		}
		return valorTotal;
	}

    /**
     * Retorna a lista de itens do carrinho de compras.
     *
     * @return itens
     */
	public Collection<Item> getItens() {
		return itensCarrinho;
	}

	private Item itemProdutoExisteEmCarrinho (Produto produto) {
		for (Item item : itensCarrinho) {
			if (item.getProduto().equals(produto)) {
				return item;
			}
		}
		return null;
	}

}