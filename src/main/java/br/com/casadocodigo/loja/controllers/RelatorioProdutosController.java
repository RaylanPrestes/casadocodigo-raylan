package br.com.casadocodigo.loja.controllers;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Controller
@RequestMapping(method = RequestMethod.GET)
public class RelatorioProdutosController {

	@Autowired
	private ProdutoDAO produtoDao;

	@RequestMapping(value = "/relatorio-produtos", produces = "application/json")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public ModelAndView relatorioJsonPorData(@RequestParam(value = "data", required = false) Calendar dataLancamento) {

		ModelAndView modelAndView = new ModelAndView();

		long dataGeracao = System.currentTimeMillis();
		
		modelAndView.addObject("dataGeracao", dataGeracao);

		if (dataLancamento != null) {
			List<Produto> produtosPorData = produtoDao.listarPorData(dataLancamento);
			modelAndView.addObject("produtos", produtosPorData);
			modelAndView.addObject("quantidade", produtosPorData.size());
		} else {
			List<Produto> produtos = produtoDao.listar();
			modelAndView.addObject("produtos", produtos);
			modelAndView.addObject("quantidade", produtos.size());
		}

		return modelAndView;
	}

}
