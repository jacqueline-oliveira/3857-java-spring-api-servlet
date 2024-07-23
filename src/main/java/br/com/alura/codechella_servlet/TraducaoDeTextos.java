package br.com.alura.codechella_servlet;

import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import com.deepl.api.Translator;

public class TraducaoDeTextos {

    public static String obterTraducao(String texto, String idioma) throws DeepLException, InterruptedException {
        String authKey = System.getenv("DEEPL_APIKEY");
        Translator tradutor = new Translator(authKey);
        TextResult result = tradutor.translateText(texto, "pt", idioma);
        return result.getText();

    }
}
