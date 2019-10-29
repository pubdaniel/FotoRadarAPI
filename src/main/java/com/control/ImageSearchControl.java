package main.java.com.control;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.java.com.dao.ImageDAO;
import main.java.com.dao.PageWithMatchingImagesDAO;
import main.java.com.model.Image;
import main.java.com.model.PageWithMatchingImages;
import main.java.com.model.dto.google.PageWithMatchingImagesDTO;
import main.java.service.GoogleClientAPI;

public class ImageSearchControl {

    private String url;

    public ImageSearchControl(String url) {
        this.url = url;
    }

    public ImageSearchControl() {}

    public static List<PageWithMatchingImages> searchPagesWithMatchingImages(String url)
            throws ClientProtocolException, IOException, ParseException {
        
    	ImageDAO dao = new ImageDAO();
    	Image i = new Image(url);
    	Image image = dao.salvar(i);
    	
    	Gson gson = new Gson();

        String json = GoogleClientAPI.detectWebDetections(url);

        // String json = ImageSearchControl.getJsonTeste();//teste mock
        System.out.println(json);

        PageWithMatchingImagesDAO pageWithMatchingImagesDAO = new PageWithMatchingImagesDAO();

        List<PageWithMatchingImages> pagesWithMatchingImages = getPagesWithMatchingImages(convertJsonToPagesWithMatchingImages(json));
        pagesWithMatchingImages.forEach(p -> {
            p.setImage(image);
            pageWithMatchingImagesDAO.salvar(p);
        });

        return pagesWithMatchingImages;

    }

    private static List<PageWithMatchingImages> getPagesWithMatchingImages(List<PageWithMatchingImagesDTO> pagesWithMatchingImages) {
        List<PageWithMatchingImages> pageWithMatchingImages = new ArrayList<>();

        pagesWithMatchingImages.forEach(pDTO -> {
            pageWithMatchingImages.add(new PageWithMatchingImages(pDTO));
        });

        return pageWithMatchingImages;
    }

    public String getUrl() {
        return url;
    }

    // private static String getJsonTeste() {
    // return "{\r\n" +
    // " \"responses\": [\r\n" +
    // " {\r\n" +
    // " \"webDetection\": {\r\n" +
    // " \"webEntities\": [\r\n" +
    // " {\r\n" +
    // " \"entityId\": \"/m/07qcyv\",\r\n" +
    // " \"score\": 0.8548,\r\n" +
    // " \"description\": \"Correios\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"entityId\": \"/t/29s5tsbvnkxym\",\r\n" +
    // " \"score\": 0.7145\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"entityId\": \"/m/0149p3\",\r\n" +
    // " \"score\": 0.5234,\r\n" +
    // " \"description\": \"Strike action\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"entityId\": \"/m/04gny\",\r\n" +
    // " \"score\": 0.4725,\r\n" +
    // " \"description\": \"Trade union\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"entityId\": \"/m/0dh8s\",\r\n" +
    // " \"score\": 0.2894,\r\n" +
    // " \"description\": \"Mail\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"entityId\": \"/g/120vzx8q\",\r\n" +
    // " \"score\": 0.2641,\r\n" +
    // " \"description\": \"Arbeider\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"entityId\": \"/m/03bxgrp\",\r\n" +
    // " \"score\": 0.2189,\r\n" +
    // " \"description\": \"Company\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"entityId\": \"/g/1tf7npnn\",\r\n" +
    // " \"score\": 0.2185,\r\n" +
    // " \"description\": \"Sintect - SP\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"entityId\": \"/g/11vk8rdyr\",\r\n" +
    // " \"score\": 0.2174,\r\n" +
    // " \"description\": \"Acordo coletivo de trabalho\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"entityId\": \"/m/02m96\",\r\n" +
    // " \"score\": 0.2126,\r\n" +
    // " \"description\": \"E-commerce\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"entityId\": \"/m/01d1z6\",\r\n" +
    // " \"score\": 0.1976,\r\n" +
    // " \"description\": \"State-owned enterprise\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"entityId\": \"/m/0c38y\",\r\n" +
    // " \"score\": 0.1966,\r\n" +
    // " \"description\": \"Deliberative assembly\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"entityId\": \"/t/24g_1m01jkvz7\",\r\n" +
    // " \"score\": 0.1964\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"entityId\": \"/m/02bbq\",\r\n" +
    // " \"score\": 0.1938,\r\n" +
    // " \"description\": \"Day\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"entityId\": \"/g/1ymvq8527\",\r\n" +
    // " \"score\": 0.1901,\r\n" +
    // " \"description\": \"Mutirão\"\r\n" +
    // " }\r\n" +
    // " ],\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://varelanoticias.com.br/wp-content/uploads/2019/09/Correios.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?fit=1280%2C853&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://static.ndonline.com.br/2017/04/b2708673efd68531b69d1db217a42f9dda3a504f.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i1.wp.com/conexaopolitica.com.br/wp-content/uploads/2019/07/29213734989002-5311235.jpg?fit=1280%2C853&ssl=1\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://sagresonline.com.br/images/2018/janeiro/correios.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://s3.portalt5.com.br/imagens/correios-2.jpg?mtime=20171017070216\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.r7.com/images/2017/10/05/19ntkobrpp_97hnaeuw5c_file.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://gazetadocerrado.com.br/wp-content/uploads/2018/02/CONCURSO-CORREIOS.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://midias.gazetaonline.com.br/_midias/jpg/2017/10/04/29213734989002-5311235.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://d3pwmv7nml69wv.cloudfront.net/wp-content/uploads/2018/02/09175200/Falta-de-funcion%C3%A1rios-gera-atraso-em-CTE-dos-Correios-de-S%C3%A3o-Paulo.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i1.wp.com/conexaopolitica.com.br/wp-content/uploads/2019/07/29213734989002-5311235.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://fscl01.fonpit.de/userfiles/6675138/image/29213734989002.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://static.trt19.jus.br/fotos/5d829572c6a71.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://coyotecriativo.com/wp-content/uploads/2018/11/correios.png\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.radiogemsfm.com.br/arquivos/noticias/2.1(1).jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://static.portaldacidade.com/unsafe/1150x767/https://s3.amazonaws.com/paranavai.portaldacidade.com/img/news/2018-03/agencias-dos-correios-funcionam-normalmente-em-paranavai-5aa6d6ecdace4.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://static.portaldacidade.com/unsafe/1150x767/https://s3.amazonaws.com/brusque.portaldacidade.com/img/news/2019-08/funcionarios-dos-correios-continuam-em-estado-de-greve-e-negociam-com-o-tst-5d431df2d1859.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://gazetadocerrado.com.br/wp-content/uploads/2018/02/CONCURSO-CORREIOS-1024x682.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.oantagonista.net/uploads/2019/06/29213734989002-5311235-1024x682.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.paiquere.com.br/wp-content/uploads/2018/03/correios-1024x682.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://imagens.canaltech.com.br/195772.500033-Correios.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://static.ndonline.com.br/2018/03/cropped/9c795a7a7e23d3e97f683791b09469a5e602cbd0.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn2.folhadoes.com/arquivos/noticias/thumbs/correios_2_915x686.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.paiquere.com.br/wp-content/uploads/2018/03/correios-960x640.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i1.wp.com/conexaopolitica.com.br/wp-content/uploads/2019/07/29213734989002-5311235.jpg?resize=1000%2C600&ssl=1\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://static.tribunadoceara.com.br/wp-content/uploads/sites/2/2018/03/correios-reproducao.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://noticias.reclameaqui.com.br/uploads/403348113.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://media-exp1.licdn.com/media-proxy/ext?w=800&h=800&hash=KtoppXSZr8knNYUDeky92qh20lo%3D&ora=1%2CaFBCTXdkRmpGL2lvQUFBPQ%2CxAVta5g-0R6uwAsNzwp27qON-xan8glESY3JBmbjBHvu5IvVJGiuOdqMJuzl8QNSZ2lBzFtjK6y3QjDgGoy8KZXvdN12jpXlJI-SZwsSaFEygCRJ-d84MBYq8Lf1QMqhcidBwaFfaD28frj9dVMsBWgy8KGqGaKVLVIClHWDX8rkEMNWE7NnvbIf4A0Qhp7KWfo_3YBlhmJ5zw\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.jornaldoisirmaos.com.br/images/pequenas/presidente-bolsonaro-autoriza-estudo-para-privatizacao-dos-correios-1556301027.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://s.yimg.com/ny/api/res/1.2/o_PuFBDi8IAC7oiNzS1W6A--~A/YXBwaWQ9aGlnaGxhbmRlcjtzbT0xO3c9ODAw/https://media.zenfs.com/pt-br/canal_tech_990/8d688784b15879ba2d8c9f7f46892d66\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://fscl01.fonpit.de/userfiles/6675138/image/29213734989002-w782.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.portaltri.com.br/arquivos/noticias/107112/01.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.portaldoholanda.com.br/sites/default/files/imagecache/2016_noticia_topo/portaldoholanda-909277-imagem.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://ecommercenapratica.com/wp-content/uploads/6d7680587b3dd2d42390fcec08b01dd28c7a9456-700x466.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.paiquere.com.br/wp-content/uploads/2018/03/correios-700x466.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://4.bp.blogspot.com/-pmh9ItIoRzg/Web5NujJLNI/AAAAAAAAUAE/NTcXaHqu1fEoWbz11m13IMVqECpJ_VVNgCK4BGAYYCw/s640/29213734989002.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/correiodoimbe.com.br/wp-content/uploads/2018/05/correios_ebc-631x420.jpg?resize=631%2C420&ssl=1\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/correiodoimbe.com.br/wp-content/uploads/2018/05/correios_ebc-631x420.jpg?fit=631%2C420&ssl=1\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://correiodoimbe.com.br/wp-content/uploads/2018/05/correios_ebc-631x420.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://3.bp.blogspot.com/-8z5KcNr8Bnw/WdacubF-q8I/AAAAAAAAXEY/nNGU3xVmuSMA0_YBookZiSrs8VXVOKvOACLcBGAs/s1600/59d64932bf876a01a3009321.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://3.bp.blogspot.com/-8z5KcNr8Bnw/WdacubF-q8I/AAAAAAAAXEY/nNGU3xVmuSMA0_YBookZiSrs8VXVOKvOACLcBGAs/s640/59d64932bf876a01a3009321.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.jequieurgente.com/wp-content/uploads/2019/08/Imagem-Redimensionada-167-700x350.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.jequieurgente.com/wp-content/uploads/2019/08/Imagem-Redimensionada-167.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://scontent-lax3-1.cdninstagram.com/vp/9f59231b6d950634027366425a2d0ab1/5E1D0095/t51.2885-15/e35/69218814_130246788275269_6775898291381311118_n.jpg?_nc_ht=scontent-lax3-1.cdninstagram.com&_nc_cat=101\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://esplanadanews.com.br/portal/fotos/CONCURSO-CORREIOS-redenewsonline800.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?fit=590%2C393&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.programadogoverno.org/wp-content/uploads/2017/10/Correios-anuncia-encerramento-do-Banco-Postal-em-mais-de-18-mil-ag%C3%AAncias-e1507210613113.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.conceicaoverdade.com.br/wp-content/uploads/2019/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://br.advfn.com/jornal/files/2019/03/correios-2-585x390.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://static.portaldacidade.com/unsafe/560x374/https://s3.amazonaws.com/brusque.portaldacidade.com/img/news/2019-08/funcionarios-dos-correios-continuam-em-estado-de-greve-e-negociam-com-o-tst-5d431df2d1859.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://static.portaldacidade.com/unsafe/560x374/https://s3.amazonaws.com/paranavai.portaldacidade.com/img/news/2018-03/agencias-dos-correios-funcionam-normalmente-em-paranavai-5aa6d6ecdace4.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.oantagonista.net/uploads/2019/06/29213734989002-5311235-530x353.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.jequieurgente.com/wp-content/uploads/2019/08/CORREIOS-526x350.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.oficinadanet.com.br/imagens/post/22655/correios_750x469_5aecb4fdbe7bb_500312.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn2.folhadoes.com/arquivos/noticias/thumbs/correios_2_400x300.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://content-thumbnail.cxpublic.com/content/dominantthumbnail/28e64eb0cc946a42f54075a1f11b94859c9d1078.jpg?5af0a896\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.rastrearobjetos.com.br/blog/wp-content/uploads/2017/09/caixa-correios-amarela-e-azul-300x200.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.liegebarbalho.com/wp-content/uploads/2018/01/7f3f4809b3b73c642139af4e8508a4cc.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://imediatoonline.com/wp-content/uploads/2019/05/download.jpeg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://contilnetnoticias.com.br/wp-content/uploads/2018/02/CONCURSO-CORREIOS-150x95.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?resize=125%2C82&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " }\r\n" +
    // " ],\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://adm.rc24h.com.br/_upl/_images/2019/10/04/92c8e628e902f4225a35728c87da563b.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.ibahia.com/fileadmin/user_upload/ibahia/2019/maio/06/correios1.jpg?width=1200&enable=upscale\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://novo.org.br/wp-content/uploads/2019/08/correios.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://s.yimg.com/ny/api/res/1.2/2bqZP23INrIn5pDd.AgRBw--/YXBwaWQ9aGlnaGxhbmRlcjt3PTEyODA7aD03MjA-/https://s.yimg.com/uu/api/res/1.2/Fa3YAwdoWUs9SgQSx3OUow--~B/aD03MjA7dz0xMjgwO3NtPTE7YXBwaWQ9eXRhY2h5b24-/https://media.zenfs.com/pt-br/canal_tech_990/f3623f01efc419093ebc20a3c7b47058\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.meubalneariocamboriu.com.br/wp-content/uploads/2018/07/correios-1280x720.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://s.yimg.com/uu/api/res/1.2/Fa3YAwdoWUs9SgQSx3OUow--~B/aD03MjA7dz0xMjgwO3NtPTE7YXBwaWQ9eXRhY2h5b24-/https://media.zenfs.com/pt-br/canal_tech_990/f3623f01efc419093ebc20a3c7b47058\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://s.yimg.com/uu/api/res/1.2/WZkTlJsA._bms830nnbDzQ--~B/aD03MjA7dz0xMjc5O3NtPTE7YXBwaWQ9eXRhY2h5b24-/https://media.zenfs.com/pt-br/canal_tech_990/35bd7f7e1060b69d605badbabdd1b3a3\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://sentinelacapixaba.com.br/wp-content/uploads/2018/07/c0ba5930-a53c-0135-2f72-6231c35b6685-minified-1280x650.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.rioverdeagora.com.br/uploads/noticias/3b7071c9c2db480a89575bf2e09778e0_1564658418.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://hojees.com.br/site/wordpress/wp-content/uploads/2019/09/norreios2.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://hojees.com.br/site/wordpress/wp-content/uploads/2019/09/norreios2-1024x721.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://imgcdn.portalt5.com.br/4VveQeuZLa6ptaOujcOJH-cKltU=/1200x600/smart/filters:strip_icc()/s3.portalt5.com.br/imagens/correios-2.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://farm66.staticflickr.com/65535/47629282642_032ea8ae72_b.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2017/11/21/21095137556032.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://patriapaulista.com/wp-content/uploads/2017/12/correios-1000x600.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.causaoperaria.org.br/wp-content/uploads/2019/05/correios-privatizado-2-1-1024x585.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.oimpactocruzeiro.com.br/portal/images/noticias/2019/09_setembro/07-09-2019_policia_pf_correios.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://taroba-news-cdn.s3.amazonaws.com/uploads/noticias/lg-9b9bf918-b202-42b7-a6a0-305ba5635556.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://taroba-news-cdn.s3.amazonaws.com/uploads/noticias/lg-bafeeec2-76eb-4f04-a921-4398d1c061eb.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://imgcdn.portalt5.com.br/9EMm2i45L5Hd-eYCs-yNXF1-r0g=/800x600/smart/filters:strip_icc()/s3.portalt5.com.br/imagens/correios-2.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://i2.wp.com/grnews.com.br/wp-content/uploads/2017/08/correios.jpg?fit=955%2C500&ssl=1\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://emc.acidadeon.com/dbimagens/agencia_dos_790x505_22032019081710.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://imirante.com/imagens/2019/03/28/1553786531-485758118-810x471.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://i2.wp.com/grnews.com.br/wp-content/uploads/2017/08/correios.jpg?fit=850%2C445&ssl=1\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://fscl01.fonpit.de/userfiles/6675138/image/29213734989002-w810h462.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://portalgiro.com/wp-content/uploads/2019/10/Octac%C3%ADlio-Barbosa-correios.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.odiariocarioca.com/wp-content/uploads/2019/10/unnamed-7.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.alerj.rj.gov.br/Uploads/Publicacao/Imagem/media_03102019_122956correios-750x410.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://jornalatual.com.br/wp-content/uploads/2019/10/SERVI%C3%87O-CRLV-dever%C3%A1-ser-enviado-pelos-correios-sem-custo-adicional.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.osaogoncalo.com.br/img/normal/60000/otacilio-barbosa_00063671_0.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://hora1pb.com.br/wp-content/uploads/2019/09/IMG_04092019_105423_800_x_500_pixel-800x445.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://comandonoticia.com.br/wp-content/uploads/2018/03/correios-750x450.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.ibahia.com/fileadmin/user_upload/ibahia/2019/maio/06/correios1.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://s2.rondoniaovivo.com/rm3tJG-pzpArvhezu-fmy9gVTS8=/750x423/filters:contrast(10)/rondoniaovivo.com/media/fotos/2018/5/10/Correios-300x200.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://bahianoar.com/wp-content/uploads/2019/08/correios-750x410.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.reporterceara.com.br/wp-content/uploads/2019/08/005-2.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://timeline.canaltech.com.br/269256.700/correios-lanca-nova-ferramenta-que-permite-consultar-encomendas-via-cpf-ou-cnpj.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://timeline.canaltech.com.br/321123.700/correios-bolsonaro-cede-e-pode-permitir-privatizacao-da-estatal-137621.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://imagem.band.com.br/f_464417.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://uploads.bemparana.com.br/upload/image/noticia/noticia_570111_img1_correios-2.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.sul21.com.br/wp-content/uploads/2019/04/20190424-correios_div.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://farm66.staticflickr.com/65535/47629282642_032ea8ae72_z.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://bahianoar.com/wp-content/uploads/2019/08/correios-750x410-678x381.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.r7.com/images/folha-vitoria-31072019135336802?dimensions=660x360\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://1.bp.blogspot.com/-ENKyytLZPNE/XZZL2bOmgEI/AAAAAAABGnU/-lTHQzQmxOoRwQMOze6WXe2MpjiS167hACLcBGAsYHQ/s640/CRLVR-PELOS-CORREIOS.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.sideralfm.com.br/upload/noticia/12506/01_323182.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://imirante.com/imagens/2019/03/28/1553786531-485758118-610x355.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i2.wp.com/grnews.com.br/wp-content/uploads/2017/08/correios.jpg?fit=955%2C500&ssl=1&w=640\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://jornalsomos.com.br/imgc/noticias/600x350/correios-fecham-nove-agencias-em-goias-ate-inicio-de-julho-veja-lista-1-e1559129994659.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://midias.gazetaonline.com.br/_midias/jpg/2017/10/04/605x330/1_29213734989002-5311235.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://coyotecriativo.com/wp-content/uploads/2018/11/correios-495x400.png\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.r7.com/images/folha-vitoria-31072019135336802?dimensions=600x315&crop_position=c\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://img.r7.com/images/2017/10/05/19ntkobrpp_97hnaeuw5c_file.jpg?dimensions=600x315&crop_position=c\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.r7.com/images/folha-vitoria-31072019135336802\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://folhaviana.com/wp-content/uploads/2019/09/d0e1fe80-20e0-0137-e556-6231c35b6685-minified.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://assets.folhavitoria.com.br/images/2be99f50-b372-0136-99aa-6231c35b6685--minified.png\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://i.ytimg.com/vi/8jFLP1y6kTs/hqdefault.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://vignews.com.br/wp-content/uploads/2019/09/d0e1fe80-20e0-0137-e556-6231c35b6685-minified-480x352.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://2.bp.blogspot.com/-JRbJn5-zL_M/XI7ayxgfHbI/AAAAAAAB95E/smcty6XBlAgEVlJNYIsjOK6r8Jg9QUzcACLcBGAs/s1600/qozg2v2c.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://thumbs.jusbr.com/480x320/imgs.jusbr.com/publications/images/aae6d6fde5047506716fd8e45ca721af\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://2.bp.blogspot.com/-JRbJn5-zL_M/XI7ayxgfHbI/AAAAAAAB95E/smcty6XBlAgEVlJNYIsjOK6r8Jg9QUzcACLcBGAs/w1200-h630-p-k-no-nu/qozg2v2c.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://lindenmeyer.adv.br/wp-content/uploads/2018/05/11124032127002.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://oimparcial.com.br/app/uploads/2018/08/correios.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://comandonoticia.com.br/wp-content/uploads/2018/03/correios-365x330.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?resize=480%2C250&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://jmonline.com.br/uploads/noticia/184187_1.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i2.wp.com/grnews.com.br/wp-content/uploads/2017/08/correios.jpg?resize=455%2C250&ssl=1\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://static.portaldacidade.com/unsafe/400x283/https://s3.amazonaws.com/brusque.portaldacidade.com/img/news/2019-08/funcionarios-dos-correios-continuam-em-estado-de-greve-e-negociam-com-o-tst-5d431df2d1859.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://poracaso.sfo2.digitaloceanspaces.com/2019/08/correios.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://midias.gazetaonline.com.br/_midias/jpg/2017/10/04/290x330/1_29213734989002-5311235.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://timeline.canaltech.com.br/321123.400/correios-bolsonaro-cede-e-pode-permitir-privatizacao-da-estatal.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://timeline.canaltech.com.br/330123.400/marcos-pontes-e-bolsonaro-divergem-sobre-privatizacao-dos-correios.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.carangondenoticias.com.br/uploads/images/image_380x226_5d7034601979f.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.conceicaoverdade.com.br/wp-content/uploads/2019/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456-390x220.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.cesargaleano.com/wp-content/uploads/2019/03/Sem-t%C3%ADtulo-1-345x240.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://lftv.com.br/wp-content/uploads/2019/08/47629282642_032ea8ae72_z-390x205.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://hojees.com.br/site/wordpress/wp-content/uploads/2019/09/norreios2-324x235.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://varelanoticias.com.br/wp-content/uploads/2019/09/Correios-360x210.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://ibahia-cdn2.cworks.cloud/fileadmin/user_upload/ibahia/2019/maio/06/correios.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.ibahia.com/fileadmin/_processed_/c/f/csm_correios1_99195268d6.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2018/03/11/11124032127002.jpg?w=352&h=208&mode=crop&scale=both\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2018/03/15/15101451250045.jpg?w=352&h=208&mode=crop&scale=both\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2017/11/21/21095110525030.jpg?w=352&h=208&mode=crop&scale=both\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://lancenoticias.com.br/wp-content/uploads/2018/07/correios-351x200.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i2.wp.com/gazetadocerrado.com.br/wp-content/uploads/2018/02/CONCURSO-CORREIOS.jpg?resize=350%2C200&ssl=1\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://s2.rondoniaovivo.com/BuNxMIH9p9ulJWxfOWJ22iMuwOQ=/353x198/smart/filters:contrast(10)/rondoniaovivo.com/media/fotos/2018/5/10/Correios-300x200.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.emtempo.com.br/img/Capa-Destaque-2/160000/correios_00169451_0_201908211217.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.portalondasul.com.br/wp-content/uploads/2018/10/correios-2-346x188.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://imirante.com/imagens/2019/03/28/1553786531-485758118-304x175.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://noticias.botucatu.com.br/wp-content/uploads/2017/10/Correios-300x160.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://infosaj.com.br/wp-content/uploads/2019/09/correios-3-218x150.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://omelhordabaixada.com.br/wp-content/uploads/2017/10/correios-1-150x150.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/ops4.com.br/wp-content/uploads/2019/03/Assaltantes-roubam-65-mil-reais-em-ag%C3%AAncia-dos-Correios-em-Santa-L%C3%BAcia-SP.jpg?resize=150%2C150&ssl=1\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.suerdamedeiros.com/wp-content/uploads/2019/08/correios-150x150.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.paiquere.com.br/wp-content/uploads/2018/03/correios-150x150.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://ecommercenapratica.com/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456-150x150.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2017/09/29/29213734989002.jpg?w=164&h=118&mode=crop&scale=both\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2017/11/21/21095137556032.jpg?w=164&h=118&mode=crop&scale=both\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2018/03/11/11124032127002.jpg?w=164&h=118&mode=crop&scale=both\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.radarcidade.com/wp-content/uploads/2019/04/323182-160x120.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://thumbs.jusbr.com/filters:format(webp)/imgs.jusbr.com/publications/images/aae6d6fde5047506716fd8e45ca721af\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.diariodacidade.com.br/wp-content/uploads/2018/07/correios.jpg?fit=1200%2C480&ssl=1\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2018/03/15/15101451250045.jpg?w=1120&h=420&mode=crop&scale=both\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2017/11/21/21095110525030.jpg?w=1120&h=420&mode=crop&scale=both\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.frenet.com.br/blog/wp-content/uploads/2018/03/correios-1024x410.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://t.tudocdn.net/323183?w=1000&fit=clip\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://radio98fm.com/site/wp-content/uploads/2018/07/correios-900x444.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.diariodacidade.com.br/wp-content/uploads/2018/07/correios.jpg?fit=960%2C384&ssl=1\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://s2.rondoniaovivo.com/Omil8dZCDMDZvC3EF8gzMIkFF_U=/615x300/filters:contrast(10)/rondoniaovivo.com/media/fotos/2018/5/10/Correios-300x200.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://yata.ostr.locaweb.com.br/9da47b65c78162b26b5aacad2707ec14203f74d0c1b5adfe6940fed89b3f7659\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://difusoramais.com/wp-content/uploads/2018/05/323183.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.radarcidade.com/wp-content/uploads/2019/04/323182.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://ilhotaonline.com.br/wp-content/uploads/2019/09/323182.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://radio98fm.com/site/wp-content/uploads/2018/07/correios-900x444-665x250.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.globalframe.com.br/gf_base/empresas/MIGA/imagens/47C47FFC5B8CED4BCC82045F0B29F5D0DB1A_correiosazul.png\"\r\n"
    // +
    // " }\r\n" +
    // " ],\r\n" +
    // " \"pagesWithMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.linkedin.com/feed/news/correios-em-greve-4444507\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e em \\u003cb\\u003egreve\\u003c/b\\u003e |
    // LinkedIn\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.oantagonista.net/uploads/2019/06/29213734989002-5311235.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://ndmais.com.br/noticias/procon-de-sao-jose-alerta-consumidores-durante-greve-dos-correios/\",\r\n" +
    // " \"pageTitle\": \"Procon de São José alerta consumidores durante \\u003cb\\u003egreve\\u003c/b\\u003e dos
    // \\u003cb\\u003eCorreios\\u003c/b\\u003e\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://static.ndonline.com.br/2017/08/b90479c3a80ce61f3fae2e12500af4f5aff933de.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://static.ndonline.com.br/2018/03/cropped/9c795a7a7e23d3e97f683791b09469a5e602cbd0.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.tecmundo.com.br/mercado/128098-greve-correios-procon-diz-usuarios-pedir-ressarcimento.htm\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eGreve\\u003c/b\\u003e dos \\u003cb\\u003eCorreios\\u003c/b\\u003e: Procon diz
    // que usuários podem pedir ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2018/03/11/11124032127002.jpg?w=164&h=118&mode=crop&scale=both\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.ecommercebrasil.com.br/noticias/correios-greve-nesta-quarta/\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e entrarão em \\u003cb\\u003egreve\\u003c/b\\u003e a
    // partir de quarta-feira | E-Commerce ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://d3pwmv7nml69wv.cloudfront.net/wp-content/uploads/2018/02/09175200/Falta-de-funcion%C3%A1rios-gera-atraso-em-CTE-dos-Correios-de-S%C3%A3o-Paulo.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.folhavitoria.com.br/geral/noticia/09/2019/funcionarios-dos-correios-entram-em-greve-no-es\",\r\n" +
    // " \"pageTitle\": \"Funcionários dos \\u003cb\\u003eCorreios\\u003c/b\\u003e entram em
    // \\u003cb\\u003egreve\\u003c/b\\u003e no ES - Folha Vitoria\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://assets.folhavitoria.com.br/images/d0e1fe80-20e0-0137-e556-6231c35b6685--minified.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.tecmundo.com.br/internet/128095-greve-correios-musk-ia-conserto-s9-tecmundo.htm\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eGreve\\u003c/b\\u003e nos \\u003cb\\u003eCorreios\\u003c/b\\u003e, Musk e IA,
    // conserto do S9+ e mais - Hoje no ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2018/03/11/11124032127002.jpg?w=164&h=118&mode=crop&scale=both\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.folhavitoria.com.br/geral/noticia/07/2019/funcionarios-dos-correios-decidem-sobre-greve-nesta-quarta-feira\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eGreve\\u003c/b\\u003e nos Correio: funcionários decidem sobre
    // \\u003cb\\u003egreve\\u003c/b\\u003e nesta quarta ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://assets.folhavitoria.com.br/images/d0e1fe80-20e0-0137-e556-6231c35b6685--minified.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.oantagonista.com/brasil/correios-suspendem-greve/\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e suspendem \\u003cb\\u003egreve\\u003c/b\\u003e - O
    // Antagonista\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.oantagonista.net/uploads/2019/06/29213734989002-5311235.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://ecommercenapratica.com/como-enfrentar-greve-dos-correios/\",\r\n" +
    // " \"pageTitle\": \"6 Dicas para Você Enfrentar a \\u003cb\\u003eGreve\\u003c/b\\u003e dos
    // \\u003cb\\u003eCorreios\\u003c/b\\u003e | Ecommerce na ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://ecommercenapratica.com/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://ecommercenapratica.com/wp-content/uploads/6d7680587b3dd2d42390fcec08b01dd28c7a9456-700x466.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://noticias.r7.com/cidades/folha-vitoria/funcionarios-dos-correios-decidem-sobre-greve-nesta-quarta-feira-31072019\",\r\n"
    // +
    // " \"pageTitle\": \"Funcionários dos \\u003cb\\u003eCorreios\\u003c/b\\u003e decidem sobre
    // \\u003cb\\u003egreve\\u003c/b\\u003e nesta quarta-feira ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.r7.com/images/folha-vitoria-31072019135336802\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.r7.com/images/folha-vitoria-31072019135336802?dimensions=600x315&crop_position=c\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.r7.com/images/folha-vitoria-31072019135336802?dimensions=660x360\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.oantagonista.com/brasil/greve-nos-correios-2/\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eGreve\\u003c/b\\u003e nos \\u003cb\\u003eCorreios\\u003c/b\\u003e - O
    // Antagonista\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.oantagonista.net/uploads/2019/06/29213734989002-5311235.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.tecmundo.com.br/mercado/128149-termina-greve-correios-decisao-justica-trabalho.htm\",\r\n" +
    // " \"pageTitle\": \"Termina \\u003cb\\u003egreve\\u003c/b\\u003e dos \\u003cb\\u003eCorreios\\u003c/b\\u003e após
    // decisão da Justiça do Trabalho ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2018/03/11/11124032127002.jpg?w=164&h=118&mode=crop&scale=both\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.ecommercebrasil.com.br/noticias/correios-greve-nao-afeta-servicos/\",\r\n" +
    // " \"pageTitle\": \"Direção dos \\u003cb\\u003eCorreios\\u003c/b\\u003e diz que
    // \\u003cb\\u003egreve\\u003c/b\\u003e não afeta serviços postais\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://d3pwmv7nml69wv.cloudfront.net/wp-content/uploads/2018/02/09175200/Falta-de-funcion%C3%A1rios-gera-atraso-em-CTE-dos-Correios-de-S%C3%A3o-Paulo.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://noticias.reclameaqui.com.br/noticias/correios-funcionarios-encerram-greve-na-maior-parte-do-pais_3177/\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e: funcionários encerram
    // \\u003cb\\u003egreve\\u003c/b\\u003e na maior parte do país ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://noticias.reclameaqui.com.br/uploads/1551915824.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://noticias.r7.com/brasil/trabalhadores-dos-correios-aceitam-acordo-e-encerram-greve-06102017\",\r\n" +
    // " \"pageTitle\": \"Trabalhadores dos \\u003cb\\u003eCorreios\\u003c/b\\u003e aceitam acordo e encerram
    // \\u003cb\\u003egreve\\u003c/b\\u003e ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.r7.com/images/2017/10/05/19ntkobrpp_97hnaeuw5c_file.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.maispb.com.br/407950/correios-farao-assembleia-por-indicativo-de-greve.html\",\r\n" +
    // " \"pageTitle\": \"MaisPB • \\u003cb\\u003eCorreios\\u003c/b\\u003e farão assembleia por indicativo de
    // \\u003cb\\u003egreve\\u003c/b\\u003e\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?resize=480%2C250&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://folhaviana.com/funcionarios-dos-correios-entram-em-greve-no-es/\",\r\n" +
    // " \"pageTitle\": \"Funcionários dos \\u003cb\\u003eCorreios\\u003c/b\\u003e entram em
    // \\u003cb\\u003egreve\\u003c/b\\u003e no ES | Folha Viana\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://folhaviana.com/wp-content/uploads/2019/09/d0e1fe80-20e0-0137-e556-6231c35b6685-minified.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://brusque.portaldacidade.com/noticias/regiao/funcionarios-dos-correios-continuam-em-estado-de-greve-e-negociam-com-o-tst-1129\",\r\n"
    // +
    // " \"pageTitle\": \"Funcionários dos \\u003cb\\u003eCorreios\\u003c/b\\u003e continuam em estado de
    // \\u003cb\\u003egreve\\u003c/b\\u003e e ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://static.portaldacidade.com/unsafe/560x374/https://s3.amazonaws.com/brusque.portaldacidade.com/img/news/2019-08/funcionarios-dos-correios-continuam-em-estado-de-greve-e-negociam-com-o-tst-5d431df2d1859.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://static.portaldacidade.com/unsafe/1150x767/https://s3.amazonaws.com/brusque.portaldacidade.com/img/news/2019-08/funcionarios-dos-correios-continuam-em-estado-de-greve-e-negociam-com-o-tst-5d431df2d1859.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.maispb.com.br/232813/tst-ve-greve-abusiva-e-correios-descontara-no-salario.html\",\r\n" +
    // " \"pageTitle\": \"MaisPB • TST vê \\u003cb\\u003egreve\\u003c/b\\u003e abusiva e
    // \\u003cb\\u003eCorreios\\u003c/b\\u003e vai descontar\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?resize=480%2C250&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.maispb.com.br/412249/funcionarios-dos-correios-suspendem-greve-apos-decisao-do-tst.html\",\r\n" +
    // " \"pageTitle\": \"MaisPB • \\u003cb\\u003eCorreios\\u003c/b\\u003e: funcionários suspendem
    // \\u003cb\\u003egreve\\u003c/b\\u003e\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?fit=590%2C393&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?resize=480%2C250&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?fit=1280%2C853&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.tecmundo.com.br/mercado/128053-correios-greve-funcionarios-comeca-segunda-afeta-brasil.htm\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e: \\u003cb\\u003egreve\\u003c/b\\u003e dos funcionários
    // começa na segunda e afeta todo o ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2018/03/11/11124032127002.jpg?w=352&h=208&mode=crop&scale=both\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://ndmais.com.br/noticias/correios-fazem-mutirao-para-colocar-em-dia-entregas-atrasadas-por-causa-de-greve/\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e fazem mutirão para colocar em dia entregas atrasadas
    // por ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://static.ndonline.com.br/2017/08/b90479c3a80ce61f3fae2e12500af4f5aff933de.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://comandonoticia.com.br/correios-trabalhadores-e-empresa-seguem-negociacoes-para-tentar-evitar-greve/\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e: trabalhadores e empresa seguem negociações para
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://comandonoticia.com.br/wp-content/uploads/2018/03/correios-750x450.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://comandonoticia.com.br/wp-content/uploads/2018/03/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://varelanoticias.com.br/correios-rejeitam-mediacao-e-categoria-pode-entrar-em-greve-na-terca-feira/\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e rejeitam mediação e categoria pode entrar em
    // \\u003cb\\u003egreve\\u003c/b\\u003e na ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://varelanoticias.com.br/wp-content/uploads/2019/09/Correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://varelanoticias.com.br/assembleia-ira-definir-adesao-de-trabalhadores-dos-correios-na-bahia-a-greve/\",\r\n"
    // +
    // " \"pageTitle\": \"Assembleia irá definir adesão de trabalhadores dos \\u003cb\\u003eCorreios\\u003c/b\\u003e na
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://varelanoticias.com.br/wp-content/uploads/2019/09/Correios-360x210.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://br.noticias.yahoo.com/sem-acordo-funcion%C3%A1rios-dos-correios-110113774.html\",\r\n" +
    // " \"pageTitle\": \"Sem acordo, funcionários dos \\u003cb\\u003eCorreios\\u003c/b\\u003e entram em
    // \\u003cb\\u003egreve\\u003c/b\\u003e em todo o ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://s.yimg.com/uu/api/res/1.2/WZkTlJsA._bms830nnbDzQ--~B/aD03MjA7dz0xMjc5O3NtPTE7YXBwaWQ9eXRhY2h5b24-/https://media.zenfs.com/pt-br/canal_tech_990/35bd7f7e1060b69d605badbabdd1b3a3\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://comandonoticia.com.br/funcionarios-dos-correios-iniciam-greve-em-todo-o-pais/\",\r\n" +
    // " \"pageTitle\": \"Funcionários dos \\u003cb\\u003eCorreios\\u003c/b\\u003e iniciam
    // \\u003cb\\u003egreve\\u003c/b\\u003e em todo o País - Comando ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://comandonoticia.com.br/wp-content/uploads/2018/03/correios-750x450.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://comandonoticia.com.br/wp-content/uploads/2018/03/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://comandonoticia.com.br/correios-afirmam-que-entregas-estao-normalizadas-em-indaiatuba-apos-greve/\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e afirmam que entregas estão normalizadas em Indaiatuba
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://comandonoticia.com.br/wp-content/uploads/2018/03/correios-750x450.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://comandonoticia.com.br/wp-content/uploads/2018/03/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://varelanoticias.com.br/funcionarios-dos-correios-anunciam-greve-a-partir-desta-quarta-feira/\",\r\n" +
    // " \"pageTitle\": \"Funcionários dos \\u003cb\\u003eCorreios\\u003c/b\\u003e anunciam
    // \\u003cb\\u003egreve\\u003c/b\\u003e a partir desta quarta ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://varelanoticias.com.br/wp-content/uploads/2019/09/Correios-360x210.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.maispb.com.br/248646/correios-entram-em-greve-e-mais-de-350-mil-correspondencias-nao-serao-entregues-em-jp.html\",\r\n"
    // +
    // " \"pageTitle\": \"MaisPB • \\u003cb\\u003eGreve\\u003c/b\\u003e dos \\u003cb\\u003eCorreios\\u003c/b\\u003e
    // encalha correspondências na PB\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?fit=590%2C393&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?resize=480%2C250&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?fit=1280%2C853&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.portalviu.com.br/economia/funcionarios-dos-correios-em-greve\",\r\n" +
    // " \"pageTitle\": \"Funcionários dos \\u003cb\\u003eCorreios\\u003c/b\\u003e em
    // \\u003cb\\u003egreve\\u003c/b\\u003e – Portal Viu\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.portalviu.com.br/wp-content/uploads/2018/03/Correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://leouve.com.br/assembleia-pode-definir-destino-da-greve-dos-correios-nesta-terca-feira/\",\r\n" +
    // " \"pageTitle\": \"Assembleia pode definir destino da \\u003cb\\u003egreve\\u003c/b\\u003e dos
    // \\u003cb\\u003eCorreios\\u003c/b\\u003e nesta terça ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.leouve.com.br/2019/09/17143405/6d7680587b3dd2d42390fcec08b01dd28c7a9456-1.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.gazetaonline.com.br/noticias/brasil/2017/10/empregados-dos-correios-voltam-ao-trabalho-apos-fim-da-greve-1014102995.html\",\r\n"
    // +
    // " \"pageTitle\": \"Empregados dos \\u003cb\\u003eCorreios\\u003c/b\\u003e voltam ao trabalho após fim da
    // \\u003cb\\u003egreve\\u003c/b\\u003e | A ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://midias.gazetaonline.com.br/_midias/jpg/2017/10/04/29213734989002-5311235.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.gazetaonline.com.br/noticias/brasil/2017/10/greve-dos-correios-chega-ao-fim-apos-17-dias-de-paralisacao-1014102730.html\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eGreve\\u003c/b\\u003e dos \\u003cb\\u003eCorreios\\u003c/b\\u003e chega ao fim
    // após 17 dias de paralisação ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://midias.gazetaonline.com.br/_midias/jpg/2017/10/04/29213734989002-5311235.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://ndmais.com.br/noticias/greve-dos-funcionarios-dos-correios-adesao-e-de-50-em-santa-catarina-segundo-sindicato/\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eGreve\\u003c/b\\u003e dos funcionários dos
    // \\u003cb\\u003eCorreios\\u003c/b\\u003e: adesão é de 50% em Santa ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://static.ndonline.com.br/2017/04/cropped/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://static.ndonline.com.br/2017/04/b2708673efd68531b69d1db217a42f9dda3a504f.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.folhavitoria.com.br/geral/noticia/09/2019/correios-entram-com-acao-de-dissidio-de-greve-no-tst\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e entram com ação de dissídio de
    // \\u003cb\\u003egreve\\u003c/b\\u003e no TST - Folha Vitoria\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://assets.folhavitoria.com.br/images/d0e1fe80-20e0-0137-e556-6231c35b6685--minified.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://tarobanews.com/noticias/parana/trabalhadores-dos-correios-devem-entrar-em-greve-a-partir-desta-quarta-5oJQo.html\",\r\n"
    // +
    // " \"pageTitle\": \"Trabalhadores dos \\u003cb\\u003eCorreios\\u003c/b\\u003e devem entrar em
    // \\u003cb\\u003egreve\\u003c/b\\u003e a partir desta ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://taroba-news-cdn.s3.amazonaws.com/uploads/noticias/lg-9b9bf918-b202-42b7-a6a0-305ba5635556.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.es1.com.br/index.php/noticia/conteudo/9288/funcionarios-dos-correios-entram-em-greve-no-es\",\r\n"
    // +
    // " \"pageTitle\": \"Funcionários dos \\u003cb\\u003eCorreios\\u003c/b\\u003e entram em
    // \\u003cb\\u003egreve\\u003c/b\\u003e no ES - ES1\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.es1.com.br/assets/noticias/9288/u3Pe0TlEUcVF.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.opopularjm.com.br/correios-entram-com-acao-de-dissidio-de-greve-no-tst/\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e em João Monlevade não adere a
    // \\u003cb\\u003egreve\\u003c/b\\u003e, mas pode haver ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.opopularjm.com.br/wp-content/uploads/2017/09/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://noticias.r7.com/cidades/folha-vitoria/funcionarios-dos-correios-entram-em-greve-no-es-11092019\",\r\n"
    // +
    // " \"pageTitle\": \"Funcionários dos \\u003cb\\u003eCorreios\\u003c/b\\u003e entram em
    // \\u003cb\\u003egreve\\u003c/b\\u003e no ES - Cidades - R7 ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.r7.com/images/folha-vitoria-11092019093841479\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.r7.com/images/folha-vitoria-11092019093841479?dimensions=600x315&crop_position=c\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.r7.com/images/folha-vitoria-11092019093841479?dimensions=660x360\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.maispb.com.br/249215/procon-notifica-correios-durante-greve-dos-funcionarios.html\",\r\n" +
    // " \"pageTitle\": \"MaisPB • Procon-JP notifica \\u003cb\\u003eCorreios\\u003c/b\\u003e durante
    // \\u003cb\\u003egreve\\u003c/b\\u003e\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?fit=590%2C393&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?fit=1280%2C853&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.tudocelular.com/mercado/noticias/n125194/greve-caminhoneiros-diesel-suspensao-correios.html\",\r\n"
    // +
    // " \"pageTitle\": \"E agora? \\u003cb\\u003eGreve\\u003c/b\\u003e de caminhoneiros faz
    // \\u003cb\\u003eCorreios\\u003c/b\\u003e suspender ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://t.tudocdn.net/323183?w=1000&fit=clip\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://leouve.com.br/correios-entram-com-acao-de-dissidio-de-greve-no-tst/\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e entram com ação de dissídio de
    // \\u003cb\\u003egreve\\u003c/b\\u003e no TST | Portal ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.leouve.com.br/2019/09/12075205/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.paiquere.com.br/acordo-evita-greve-dos-carteiros-que-seguem-negociando-com-os-correios/\",\r\n" +
    // " \"pageTitle\": \"Acordo evita \\u003cb\\u003egreve\\u003c/b\\u003e dos carteiros que seguem negociando com os
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.paiquere.com.br/wp-content/uploads/2018/03/correios-150x150.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.paiquere.com.br/wp-content/uploads/2018/03/correios-960x640.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.paiquere.com.br/wp-content/uploads/2018/03/correios-1024x682.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.paiquere.com.br/wp-content/uploads/2018/03/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://ndmais.com.br/noticias/correios-ingressam-com-acao-de-dissidio-coletivo-contra-greve-de-trabalhadores/\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e ingressam com ação de dissídio coletivo contra
    // \\u003cb\\u003egreve\\u003c/b\\u003e de ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://static.ndonline.com.br/2017/04/b2708673efd68531b69d1db217a42f9dda3a504f.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://canaltech.com.br/governo/privatizacao-dos-correios-e-criticada-por-trabalhadores-do-setor-146381/\",\r\n"
    // +
    // " \"pageTitle\": \"Privatização dos \\u003cb\\u003eCorreios\\u003c/b\\u003e é criticada por trabalhadores do
    // setor\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://imagens.canaltech.com.br/195772.500033-Correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://grnews.com.br/11092019/grnews/direcao-diz-que-greve-dos-correios-nao-afeta-servicos-postais\",\r\n" +
    // " \"pageTitle\": \"Direção diz que \\u003cb\\u003egreve\\u003c/b\\u003e dos
    // \\u003cb\\u003eCorreios\\u003c/b\\u003e não afeta serviços postais ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://i2.wp.com/grnews.com.br/wp-content/uploads/2017/08/correios.jpg?fit=955%2C500&ssl=1\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i2.wp.com/grnews.com.br/wp-content/uploads/2017/08/correios.jpg?fit=955%2C500&ssl=1&w=640\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://i2.wp.com/grnews.com.br/wp-content/uploads/2017/08/correios.jpg?fit=850%2C445&ssl=1\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.sideralfm.com.br/noticias/noticia/id:12470;trabalhadores-dos-correios-decretam-greve-nacional.html\",\r\n"
    // +
    // " \"pageTitle\": \"Trabalhadores dos \\u003cb\\u003eCorreios\\u003c/b\\u003e decretam
    // \\u003cb\\u003egreve\\u003c/b\\u003e nacional - Sideral Fm\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.sideralfm.com.br/upload/noticia/12506/01_323182.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.portaltri.com.br/noticia/107112/correios-sindicato-de-sc-decide-se-adere-a-greve-em-assembleia-nesta-terca\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e: sindicato de SC decide se adere à
    // \\u003cb\\u003egreve\\u003c/b\\u003e em assembleia ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.portaltri.com.br/arquivos/noticias/107112/01.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.sideralfm.com.br/noticias/noticia/id:12506;funcionarios-dos-correios-suspendem-greve.html\",\r\n" +
    // " \"pageTitle\": \"Funcionários dos \\u003cb\\u003eCorreios\\u003c/b\\u003e suspendem
    // \\u003cb\\u003egreve\\u003c/b\\u003e - www.sideralfm.com.br\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.sideralfm.com.br/upload/noticia/12506/01_323182.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://patriapaulista.com/funcionarios-dos-correios-em-greve-nesta-segunda/\",\r\n" +
    // " \"pageTitle\": \"Funcionários dos \\u003cb\\u003eCorreios\\u003c/b\\u003e em
    // \\u003cb\\u003egreve\\u003c/b\\u003e nesta segunda - Pátria Paulista\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://patriapaulista.com/wp-content/uploads/2017/12/correios-1000x600.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.maispb.com.br/271783/servidores-dos-correios-podem-entrar-em-greve-a-partir-desta-quarta-feira.html\",\r\n"
    // +
    // " \"pageTitle\": \"MaisPB • Servidores dos \\u003cb\\u003eCorreios\\u003c/b\\u003e devem entrar em
    // \\u003cb\\u003egreve\\u003c/b\\u003e\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?resize=480%2C250&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://ndmais.com.br/noticias/trabalhadores-dos-correios-avaliam-nesta-terca-nova-proposta-da-empresa\",\r\n"
    // +
    // " \"pageTitle\": \"Trabalhadores dos \\u003cb\\u003eCorreios\\u003c/b\\u003e avaliam nesta terça nova proposta da
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://static.ndonline.com.br/2017/04/b2708673efd68531b69d1db217a42f9dda3a504f.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.oficinadanet.com.br/correios\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e - tudo sobre - Oficina da Net\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.oficinadanet.com.br/imagens/post/22655/correios_750x469_5aecb4fdbe7bb_500312.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.oantagonista.com/tag/greve/\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003egreve\\u003c/b\\u003e Tag - O Antagonista\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.oantagonista.net/uploads/2019/06/29213734989002-5311235.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://jornalentrevista.com.br/v4/funcionarios-dos-correios-entram-em-greve-no-es/\",\r\n" +
    // " \"pageTitle\": \"Funcionários dos \\u003cb\\u003eCorreios\\u003c/b\\u003e entram em
    // \\u003cb\\u003egreve\\u003c/b\\u003e no ES – Jornal Entrevista\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://jornalentrevista.com.br/v4/wp-content/uploads/2019/09/d0e1fe80-20e0-0137-e556-6231c35b6685-minified-1.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.maispb.com.br/233464/servidores-dos-correios-encerram-greve-e-retornam-ao-trabalho-na-segunda.html\",\r\n"
    // +
    // " \"pageTitle\": \"MaisPB • Servidores dos \\u003cb\\u003eCorreios\\u003c/b\\u003e acabam
    // \\u003cb\\u003egreve\\u003c/b\\u003e e retornam 2ª\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?fit=590%2C393&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?fit=1280%2C853&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://hojees.com.br/2019/09/11/funcionarios-dos-correios-entram-em-greve-por-tempo-indeterminado/\",\r\n" +
    // " \"pageTitle\": \"Funcionários dos \\u003cb\\u003eCorreios\\u003c/b\\u003e entram em
    // \\u003cb\\u003egreve\\u003c/b\\u003e por tempo ... - Hoje ES\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://hojees.com.br/site/wordpress/wp-content/uploads/2019/09/norreios2-1024x721.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://hojees.com.br/site/wordpress/wp-content/uploads/2019/09/norreios2.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.maispb.com.br/249495/correios-voltam-as-atividades-nesta-terca.html\",\r\n" +
    // " \"pageTitle\": \"MaisPB • \\u003cb\\u003eCorreios\\u003c/b\\u003e voltam às atividades nesta terça\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?fit=590%2C393&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?fit=1280%2C853&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://mmeira.adv.br/greve-dos-correios-veja-alguns-direitos-e-deveres-do-consumidor/\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eGreve\\u003c/b\\u003e dos \\u003cb\\u003eCorreios\\u003c/b\\u003e: veja alguns
    // direitos e deveres do consumidor ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://mmeira.adv.br/wp-content/uploads/2018/03/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.conceicaoverdade.com.br/funcionarios-dos-correios-suspendem-greve-apos-decisao-do-tst/\",\r\n" +
    // " \"pageTitle\": \"Funcionários dos \\u003cb\\u003eCorreios\\u003c/b\\u003e suspendem
    // \\u003cb\\u003egreve\\u003c/b\\u003e após decisão do TST ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.conceicaoverdade.com.br/wp-content/uploads/2019/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.maispb.com.br/408420/privatizacao-deve-fechar-agencias-do-correios-no-interior-preve-sindicato.html\",\r\n"
    // +
    // " \"pageTitle\": \"MaisPB • Privatização deve fechar \\u003cb\\u003eCorreios\\u003c/b\\u003e no interior\",\r\n"
    // +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?resize=480%2C250&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.maispb.com.br/231856/procon-jp-quer-manter-servicos-dos-correios-durante-greve.html\",\r\n" +
    // " \"pageTitle\": \"MaisPB • Procon-JP quer manter serviços dos \\u003cb\\u003eCorreios\\u003c/b\\u003e\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?fit=590%2C393&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?fit=1280%2C853&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://ndmais.com.br/noticias/trabalhadores-dos-correios-avaliam-nesta-terca-nova-proposta-da-empresa/\",\r\n"
    // +
    // " \"pageTitle\": \"Trabalhadores dos \\u003cb\\u003eCorreios\\u003c/b\\u003e avaliam nesta terça nova proposta da
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://static.ndonline.com.br/2017/04/b2708673efd68531b69d1db217a42f9dda3a504f.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.tecmundo.com.br/mercado/128629-correios-celular-lancar-proprios-servicos-financeiros-digitais.htm\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e Celular vai lançar sua própria conta para transações
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2017/11/21/21095137556032.jpg?w=164&h=118&mode=crop&scale=both\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://bandrs.band.com.br/noticias/100000970274/.html\",\r\n" +
    // " \"pageTitle\": \"Trabalhadores dos \\u003cb\\u003eCorreios\\u003c/b\\u003e entram em
    // \\u003cb\\u003egreve\\u003c/b\\u003e após desacordo entre ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://imagem.band.com.br/f_464417.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.tecmundo.com.br/mercado/128205-mercado-livre-reajusta-tabela-tarifas-correios-derrubarem-liminar.htm\",\r\n"
    // +
    // " \"pageTitle\": \"Mercado Livre reajusta tabela de tarifas após \\u003cb\\u003eCorreios\\u003c/b\\u003e
    // derrubarem ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2018/03/11/11124032127002.jpg?w=164&h=118&mode=crop&scale=both\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2018/03/15/15101451250045.jpg?w=352&h=208&mode=crop&scale=both\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2018/03/15/15101451250045.jpg?w=1120&h=420&mode=crop&scale=both\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://gazetadocerrado.com.br/greve-dos-correios-ministro-do-tst-determina-volta-ao-trabalho-de-70-dos-funcionarios/\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eGreve\\u003c/b\\u003e dos \\u003cb\\u003eCorreios\\u003c/b\\u003e: Ministro do
    // TST determina volta ao trabalho de ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i2.wp.com/gazetadocerrado.com.br/wp-content/uploads/2018/02/CONCURSO-CORREIOS.jpg?resize=350%2C200&ssl=1\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.conceicaoverdade.com.br/funcionarios-dos-correios-suspendem-greve-apos-decisao-do-tst/\",\r\n" +
    // " \"pageTitle\": \"Funcionários dos \\u003cb\\u003eCorreios\\u003c/b\\u003e suspendem
    // \\u003cb\\u003egreve\\u003c/b\\u003e após decisão do TST ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.conceicaoverdade.com.br/wp-content/uploads/2019/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://br.advfn.com/jornal/tag/correios\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e | ADVFN News\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://br.advfn.com/jornal/files/2019/03/correios-2-585x390.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.ecommercebrasil.com.br/noticias/correios-anunciam-reajuste-tarifas/\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e anunciam reajuste de tarifas em 6,34% | E-Commerce
    // Brasil\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://d3pwmv7nml69wv.cloudfront.net/wp-content/uploads/2018/02/09175200/Falta-de-funcion%C3%A1rios-gera-atraso-em-CTE-dos-Correios-de-S%C3%A3o-Paulo.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.tecmundo.com.br/mercado/128121-correios-reclamam-camelos-eletronicos-fretes-gratis-china.htm\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e reclamam de “camelôs eletrônicos” com fretes grátis da
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2018/03/11/11124032127002.jpg?w=164&h=118&mode=crop&scale=both\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2017/09/29/29213734989002.jpg?w=164&h=118&mode=crop&scale=both\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.oficinadanet.com.br/correio\",\r\n" +
    // " \"pageTitle\": \"Correio - Oficina da Net\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.oficinadanet.com.br/imagens/post/22655/correios_750x469_5aecb4fdbe7bb_500312.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.tecmundo.com.br/correios/mais-lidas?page=3\",\r\n" +
    // " \"pageTitle\": \"Mais lidas hoje de \\u003cb\\u003eCorreios\\u003c/b\\u003e - TecMundo\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2017/11/21/21095137556032.jpg?w=164&h=118&mode=crop&scale=both\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2017/09/29/29213734989002.jpg?w=164&h=118&mode=crop&scale=both\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://comandonoticia.com.br/interior-de-sao-paulo-tem-81-do-efetivo-trabalhando-diz-correios/\",\r\n" +
    // " \"pageTitle\": \"Interior de São Paulo tem 81% do efetivo trabalhando, diz
    // \\u003cb\\u003eCorreios\\u003c/b\\u003e ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://comandonoticia.com.br/wp-content/uploads/2018/03/correios-750x450.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://comandonoticia.com.br/wp-content/uploads/2018/03/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://sitevitoria.com.br/?p=138952\",\r\n" +
    // " \"pageTitle\": \"Funcionários dos \\u003cb\\u003eCorreios\\u003c/b\\u003e entram em
    // \\u003cb\\u003egreve\\u003c/b\\u003e no ES | Site Vitória\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://sitevitoria.com.br/wp-content/uploads/2019/09/d0e1fe80-20e0-0137-e556-6231c35b6685-minified.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.tudocelular.com/mercado/noticias/n121654/correios-boicotando-mercado-livre-encomendas.html\",\r\n"
    // +
    // " \"pageTitle\": \"Vender sem poder entregar! \\u003cb\\u003eCorreios\\u003c/b\\u003e estaria
    // &quot;boicotando&quot; Mercado ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://t.tudocdn.net/323182?w=646&h=284\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.tudocelular.com/mercado/noticias/n124335/fechamento-agencias-correios-5-mil-demitidos.html\",\r\n"
    // +
    // " \"pageTitle\": \"Vai te afetar? \\u003cb\\u003eCorreios\\u003c/b\\u003e vão fechar mais de 500 agências e
    // demitir 5 ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://t.tudocdn.net/323183?w=1000&fit=clip\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.ecommercebrasil.com.br/artigos/consigo-trabalhar-sem-os-correios-no-e-commerce/\",\r\n"
    // +
    // " \"pageTitle\": \"Consigo trabalhar sem os \\u003cb\\u003eCorreios\\u003c/b\\u003e no e-commerce? | E-Commerce
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://d3pwmv7nml69wv.cloudfront.net/wp-content/uploads/2018/02/09175200/Falta-de-funcion%C3%A1rios-gera-atraso-em-CTE-dos-Correios-de-S%C3%A3o-Paulo.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://caririemdestaque.com/cd/economia/trabalhadores-aceitam-proposta-e-encerram-greve-nos-correios/\",\r\n" +
    // " \"pageTitle\": \"Trabalhadores aceitam proposta e encerram \\u003cb\\u003egreve\\u003c/b\\u003e nos
    // \\u003cb\\u003eCorreios\\u003c/b\\u003e ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://caririemdestaque.com/cd/wp-content/uploads/2017/10/29213734989002.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://rondoniaovivo.com/busca/?q=CORREIOS\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e - Rondoniaovivo.com - Notícias, Classificados e Banco
    // de ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://s2.rondoniaovivo.com/BuNxMIH9p9ulJWxfOWJ22iMuwOQ=/353x198/smart/filters:contrast(10)/rondoniaovivo.com/media/fotos/2018/5/10/Correios-300x200.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://canaltech.com.br/governo/correios-bolsonaro-cede-e-pode-permitir-privatizacao-da-estatal-137621/\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e | Bolsonaro cede e pode permitir privatização da
    // estatal\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://timeline.canaltech.com.br/321123.700/correios-bolsonaro-cede-e-pode-permitir-privatizacao-da-estatal-137621.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://tarobanews.com/noticias/parana/trabalhadores-dos-correios-de-londrina-retornam-ao-trabalho-E834W.html\",\r\n"
    // +
    // " \"pageTitle\": \"Trabalhadores dos \\u003cb\\u003eCorreios\\u003c/b\\u003e de Londrina retornam ao trabalho
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://taroba-news-cdn.s3.amazonaws.com/uploads/noticias/lg-bafeeec2-76eb-4f04-a921-4398d1c061eb.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://forum.outerspace.com.br/index.php?threads/get-ready-correios-preparam-greve-para-dia-12-03.509642/page-2\",\r\n"
    // +
    // " \"pageTitle\": \"Get ready: \\u003cb\\u003eCorreios\\u003c/b\\u003e preparam
    // \\u003cb\\u003egreve\\u003c/b\\u003e para dia 12/03 | Page 2 | Fórum ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://noticias.reclameaqui.com.br/uploads/403348113.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.portalondasul.com.br/tag/correios/\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003ecorreios\\u003c/b\\u003e - Notícias Portal Onda Sul\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.portalondasul.com.br/wp-content/uploads/2018/10/correios-2-346x188.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://hora1pb.com.br/privatizacao-dos-correios-proposta-por-bolsonaro-deve-fechar-agencias-no-interior-da-paraiba-sindicato-prepara-greve/\",\r\n"
    // +
    // " \"pageTitle\": \"Privatização dos \\u003cb\\u003eCorreios\\u003c/b\\u003e proposta por Bolsonaro deve fechar
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://hora1pb.com.br/wp-content/uploads/2019/09/IMG_04092019_105423_800_x_500_pixel-800x445.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://paranavai.portaldacidade.com/noticias/cidade/agencias-dos-correios-funcionam-normalmente-em-paranavai\",\r\n"
    // +
    // " \"pageTitle\": \"Agências dos \\u003cb\\u003eCorreios\\u003c/b\\u003e funcionam normalmente em
    // Paranavaí\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://static.portaldacidade.com/unsafe/560x374/https://s3.amazonaws.com/paranavai.portaldacidade.com/img/news/2018-03/agencias-dos-correios-funcionam-normalmente-em-paranavai-5aa6d6ecdace4.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://static.portaldacidade.com/unsafe/1150x767/https://s3.amazonaws.com/paranavai.portaldacidade.com/img/news/2018-03/agencias-dos-correios-funcionam-normalmente-em-paranavai-5aa6d6ecdace4.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://gazetadocerrado.com.br/tst-decide-que-empregados-dos-correios-devem-pagar-por-plano/\",\r\n"
    // +
    // " \"pageTitle\": \"TST decide que empregados dos \\u003cb\\u003eCorreios\\u003c/b\\u003e devem pagar por plano
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://gazetadocerrado.com.br/wp-content/uploads/2018/02/CONCURSO-CORREIOS.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://gazetadocerrado.com.br/wp-content/uploads/2018/02/CONCURSO-CORREIOS-1024x682.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://candeiasnews.com.br/index.php/2017/10/06/funcionarios-dos-correios-decidem-encerrar-greve-na-bahia-apos-17-dias/\",\r\n"
    // +
    // " \"pageTitle\": \"Funcionários dos \\u003cb\\u003eCorreios\\u003c/b\\u003e decidem encerrar
    // \\u003cb\\u003egreve\\u003c/b\\u003e na Bahia após ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://candeiasnews.com.br/wp-content/uploads/2017/10/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.sideralfm.com.br/noticias/noticia/id:12493;novas-regras-para-candidatos-a-cnh-simulador-facultativo-e-menos-aulas-praticas.html\",\r\n"
    // +
    // " \"pageTitle\": \"Novas regras para candidatos a CNH: Simulador ... - Sideral Fm\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.sideralfm.com.br/upload/noticia/12506/01_323182.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.rondoniaovivo.com/busca/?q=Correios\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e - Rondoniaovivo.com - Notícias, Classificados e Banco
    // de ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://s2.rondoniaovivo.com/BuNxMIH9p9ulJWxfOWJ22iMuwOQ=/353x198/smart/filters:contrast(10)/rondoniaovivo.com/media/fotos/2018/5/10/Correios-300x200.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://correiodoimbe.com.br/tag/correios/\",\r\n" +
    // " \"pageTitle\": \"Arquivos \\u003cb\\u003ecorreios\\u003c/b\\u003e - Correio do Imbé\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/correiodoimbe.com.br/wp-content/uploads/2018/05/correios_ebc-631x420.jpg?resize=631%2C420&ssl=1\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.cdlmirassol.com.br/Noticias/Funcionarios-dos-correios-podem-entrar-em-greve-a-partir-desta-segunda/\",\r\n"
    // +
    // " \"pageTitle\": \"De acordo com sindicato da categoria, paralisação foi aprovada em ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.cdlmirassol.com.br/fotos_bancoimagens/381.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://ecommercenapratica.com/como-o-reajuste-no-valor-do-frete-dos-correios-afeta-o-ecommerce/\",\r\n" +
    // " \"pageTitle\": \"Como o reajuste no valor do frete dos \\u003cb\\u003eCorreios\\u003c/b\\u003e afeta o
    // Ecommerce ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://ecommercenapratica.com/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456-150x150.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://varelanoticias.com.br/governo-abre-estudos-para-privatizar-correios-e-mais-oito-estatais/\",\r\n" +
    // " \"pageTitle\": \"Governo abre estudos para privatizar \\u003cb\\u003eCorreios\\u003c/b\\u003e e mais oito
    // estatais ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://varelanoticias.com.br/wp-content/uploads/2019/09/Correios-360x210.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.rastrearobjetos.com.br/blog/tags/tst\",\r\n" +
    // " \"pageTitle\": \"tst – Rastrear \\u003cb\\u003eCorreios\\u003c/b\\u003e\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.rastrearobjetos.com.br/blog/wp-content/uploads/2017/09/caixa-correios-amarela-e-azul-300x200.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://104maisfm.com.br/2018/05/23/greve-dos-caminhoneiros-causam-transtornos-em-diversas-partes-do-pais/\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eGreve\\u003c/b\\u003e dos Caminhoneiros causam transtornos em diversas partes
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://difusoramais.com/wp-content/uploads/2018/05/323183.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://varelanoticias.com.br/general-que-comandava-secretaria-da-presidencia-vai-presidir-os-correios/\",\r\n"
    // +
    // " \"pageTitle\": \"General que comandava Secretaria da Presidência vai presidir os ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://varelanoticias.com.br/wp-content/uploads/2019/09/Correios-360x210.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.maispb.com.br/232132/correios-realiza-acordo-com-federacao-de-trabalhadores-para-reajuste-de-salarios.html\",\r\n"
    // +
    // " \"pageTitle\": \"MaisPB • \\u003cb\\u003eCorreios\\u003c/b\\u003e propõe reajuste de salários para
    // servidores\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?fit=590%2C393&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?fit=1280%2C853&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://varelanoticias.com.br/correios-sofre-reajuste-de-tarifas-nos-servicos-telegraficos-e-postais/\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e sofre reajuste de tarifas nos serviços telegráficos e
    // postais ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://varelanoticias.com.br/wp-content/uploads/2019/09/Correios-360x210.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://rondoniaovivo.com/busca/?q=correio\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e - Rondoniaovivo.com - Notícias, Classificados e Banco
    // de ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://s2.rondoniaovivo.com/BuNxMIH9p9ulJWxfOWJ22iMuwOQ=/353x198/smart/filters:contrast(10)/rondoniaovivo.com/media/fotos/2018/5/10/Correios-300x200.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://104maisfm.com/2018/05/23/greve-dos-caminhoneiros-causam-transtornos-em-diversas-partes-do-pais/\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eGreve\\u003c/b\\u003e dos Caminhoneiros causam transtornos em diversas partes
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://difusoramais.com/wp-content/uploads/2018/05/323183.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://ndmais.com.br/noticias/com-inovacao-em-servicos-e-gestao-correios-buscam-saldo-positivo-em-2017/\",\r\n"
    // +
    // " \"pageTitle\": \"Com inovação em serviços e gestão, \\u003cb\\u003eCorreios\\u003c/b\\u003e buscam saldo
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://static.ndonline.com.br/2017/04/b2708673efd68531b69d1db217a42f9dda3a504f.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.rastrearobjetos.com.br/blog/tags/maranhao\",\r\n" +
    // " \"pageTitle\": \"maranhão – Rastrear \\u003cb\\u003eCorreios\\u003c/b\\u003e\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.rastrearobjetos.com.br/blog/wp-content/uploads/2017/09/caixa-correios-amarela-e-azul-300x200.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://tribunadoceara.com.br/noticias/segurancapublica/bandidos-tentam-invadir-predio-dos-correios-em-fortaleza-e-deixam-ameacas-ao-governo/\",\r\n"
    // +
    // " \"pageTitle\": \"Bandidos tentam invadir prédio dos \\u003cb\\u003eCorreios\\u003c/b\\u003e em Fortaleza e
    // deixam ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://jgdprod-us.s3.amazonaws.com/wp-content/uploads/sites/2/2018/03/correios-reproducao.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.acidadeon.com/economia/NOT,0,0,1444809,Correios+abandonam+negociacao+com+TST+e+categoria+ameaca+parar.aspx\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e abandonam negociação com TST e categoria ameaça
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://emc.acidadeon.com/dbimagens/agencia_dos_790x505_22032019081710.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.acidadeon.com/saocarlos/economia/NOT,0,0,1444809,Correios+abandonam+negociacao+com+TST+e+categoria+ameaca+parar.aspx\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e abandonam negociação com TST e categoria ameaça
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://emc.acidadeon.com/dbimagens/agencia_dos_790x505_22032019081710.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://parnaibaweb.com.br/noticias/brasil/correios-anunciam-reajuste-de-tarifas-em-6-34\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e anunciam reajuste de tarifas em 6,34% -
    // ParnaíbaWEB\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://parnaibaweb.com.br/images/imagens/2019/10-outubro-2019/correios-reajustaram-tarifa-media-do-sedex.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://noticias.r7.com/sao-paulo/pf-busca-grupo-por-roubo-a-correios-e-empresas-do-interior-03042018\",\r\n" +
    // " \"pageTitle\": \"PF busca grupo por roubo a \\u003cb\\u003eCorreios\\u003c/b\\u003e e empresas do interior
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://img.r7.com/images/2017/10/05/19ntkobrpp_97hnaeuw5c_file.jpg?dimensions=600x315&crop_position=c\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.r7.com/images/2017/10/05/19ntkobrpp_97hnaeuw5c_file.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://canaltech.com.br/podcast/podcast-canaltech/ct-news-11092019-novos-iphones-11-vs-4g-do-brasil-3059/\",\r\n"
    // +
    // " \"pageTitle\": \"CT News - 11/09/2019 (Novos iPhones 11 vs 4G do Brasil ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://timeline.canaltech.com.br/333499.400/sem-acordo-funcionarios-dos-correios-entram-em-greve-em-todo-o-brasil.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.paiquere.com.br/bolsonaro-autoriza-estudo-para-privatizacao-dos-correios/\",\r\n" +
    // " \"pageTitle\": \"Bolsonaro autoriza estudo para privatização dos \\u003cb\\u003eCorreios\\u003c/b\\u003e -
    // Portal ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.paiquere.com.br/wp-content/uploads/2018/03/correios-960x640.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.paiquere.com.br/wp-content/uploads/2018/03/correios-1024x682.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.paiquere.com.br/wp-content/uploads/2018/03/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.maispb.com.br/371584/garis-do-rio-de-janeiro-iniciam-greve-nesta-segunda.html\",\r\n" +
    // " \"pageTitle\": \"MaisPB • Garis do Rio de Janeiro iniciam \\u003cb\\u003egreve\\u003c/b\\u003e nesta
    // segunda\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?resize=480%2C250&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://sagresonline.com.br/noticias/politica/79399-correios-usuarios-ja-podem-consultar-encomendas-a-partir-do-cpf\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e: Usuários já podem consultar encomendas a partir do
    // CPF\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://sagresonline.com.br/images/2018/janeiro/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://canaltech.com.br/redes-sociais/swarm-traz-de-volta-recurso-de-prefeitura-do-foursquare-43841/\",\r\n" +
    // " \"pageTitle\": \"Swarm traz de volta recurso de prefeitura do Foursquare - Redes ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://timeline.canaltech.com.br/333499.400/sem-acordo-funcionarios-dos-correios-entram-em-greve-em-todo-o-brasil.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.portalviu.com.br/tag/greve/page/4\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eGreve\\u003c/b\\u003e – Página: 4 – Portal Viu\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn-nuneshost.viuonline.com.br/wp-content/uploads/2018/03/Correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://br.linkedin.com/in/fabiotenorio\",\r\n" +
    // " \"pageTitle\": \"Fábio Tenório - Analista de Suporte na Global Hitss - Global Hitss ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.oantagonista.net/uploads/2019/06/29213734989002-5311235.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://brusque.portaldacidade.com/noticias/regiao\",\r\n" +
    // " \"pageTitle\": \"Região - Notícias sobre a região de Brusque / SC | Portal da Cidade\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://static.portaldacidade.com/unsafe/400x283/https://s3.amazonaws.com/brusque.portaldacidade.com/img/news/2019-08/funcionarios-dos-correios-continuam-em-estado-de-greve-e-negociam-com-o-tst-5d431df2d1859.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.maispb.com.br/374017/trt-determina-retorno-imediato-de-50-dos-vigilantes-em-greve.html\",\r\n" +
    // " \"pageTitle\": \"MaisPB • TRT determina retorno de 50% dos vigilantes\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?resize=480%2C250&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://imediatoonline.com/pais/contra-golpes-correios-oferecem-suspensao-de-encomenda-em-todo-brasil/\",\r\n"
    // +
    // " \"pageTitle\": \"Contra Golpes: \\u003cb\\u003eCorreios\\u003c/b\\u003e oferecem suspensão de encomenda em
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://imediatoonline.com/wp-content/uploads/2019/05/download.jpeg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.paiquere.com.br/policia-faz-operacao-no-residencial-flores-do-campo/\",\r\n" +
    // " \"pageTitle\": \"Operação no residencial Flores do Campo termina com um homem ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.paiquere.com.br/wp-content/uploads/2018/03/correios-700x466.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://comandonoticia.com.br/correios-suspendem-sedex-corpus-mantem-coleta-pelo-menos-ate-sabado-em-indaiatuba/\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e suspendem Sedex, Corpus mantém coleta pelo menos até
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://comandonoticia.com.br/wp-content/uploads/2018/03/correios-750x450.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://comandonoticia.com.br/wp-content/uploads/2018/03/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.eqso.com.br/noticias/71920-pfbuscagrupoporrouboacorreioseempresasdointerior\",\r\n" +
    // " \"pageTitle\": \"PF busca grupo por roubo a \\u003cb\\u003eCorreios\\u003c/b\\u003e e empresas do interior
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.r7.com/images/2017/10/05/19ntkobrpp_97hnaeuw5c_file.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://comandonoticia.com.br/blog/page/369/\",\r\n" +
    // " \"pageTitle\": \"Blog - Página 369 de 592 - Comando Notícia\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://comandonoticia.com.br/wp-content/uploads/2018/03/correios-750x450.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://sagresonline.com.br/noticias/politica/80344-tst-decide-que-empregados-dos-correios-devem-pagar-por-plano-de-saude\",\r\n"
    // +
    // " \"pageTitle\": \"TST decide que empregados dos \\u003cb\\u003eCorreios\\u003c/b\\u003e devem pagar por plano
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://sagresonline.com.br/images/2018/janeiro/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://sagresonline.com.br/noticias/politica/78685-transporte-de-encomendas-agora-tem-exigencia-de-apresentacao-de-nota-fiscal\",\r\n"
    // +
    // " \"pageTitle\": \"Transporte de encomendas agora tem exigência de apresentação ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://sagresonline.com.br/images/2018/janeiro/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://ndmais.com.br/noticias/correios-vivem-crise-e-prejuizo-e-bilionario/\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e estão em crise, e prejuízo supera a casa dos bilhões |
    // ND ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://static.ndonline.com.br/2017/04/b2708673efd68531b69d1db217a42f9dda3a504f.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.gazetaonline.com.br/noticias/policia/2019/08/com-quase-30-assaltos-medo-se-instala-em-agencias-dos-correios-no-es-1014192990.html\",\r\n"
    // +
    // " \"pageTitle\": \"Com quase 30 assaltos, medo se instala em agências dos \\u003cb\\u003eCorreios\\u003c/b\\u003e
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://midias.gazetaonline.com.br/_midias/jpg/2017/10/04/29213734989002-5311235.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://midias.gazetaonline.com.br/_midias/jpg/2017/10/04/605x330/1_29213734989002-5311235.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.folhavitoria.com.br/economia/noticia/10/2019/correios-anunciam-reajuste-de-6-34-nas-tarifas\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e anunciam reajuste de 6,34% nas tarifas - Folha
    // Vitória\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://assets.folhavitoria.com.br/images/d0e1fe80-20e0-0137-e556-6231c35b6685--minified.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.sideralfm.com.br/noticias/noticia/id:12622;irma-dulce-e-canonizada-no-vaticano-e-se-torna-primeira-santa-brasileira.html\",\r\n"
    // +
    // " \"pageTitle\": \"Irmã Dulce é canonizada no Vaticano e se torna ... - Sideral Fm\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.sideralfm.com.br/upload/noticia/12506/01_323182.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.sideralfm.com.br/noticias/noticia/id:12407;em-manuteno.html\",\r\n" +
    // " \"pageTitle\": \"Aeroporto de Confins terá ônibus direto para Ouro ... - Sideral Fm\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.sideralfm.com.br/upload/noticia/12506/01_323182.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://sagresonline.com.br/noticias/politica?fb_comment_id=1131605323537602_1131834226848045&start=2745\",\r\n"
    // +
    // " \"pageTitle\": \"Política - Page #550 - Sagres Online\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://sagresonline.com.br/images/2018/janeiro/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.maispb.com.br/269215/sedex-paralisa-atividades-na-pb-e-aciona-mpt.html\",\r\n" +
    // " \"pageTitle\": \"MaisPB • Sedex paralisa atividades na Paraíba e aciona MPT\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i0.wp.com/www.maispb.com.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg?resize=480%2C250&quality=90&strip=all&ssl=1\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://leouve.com.br/serie-b-voltou-com-muitos-gols/amp/\",\r\n" +
    // " \"pageTitle\": \"Série B voltou com muitos gols | Portal Leouve\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.leouve.com.br/2019/09/17143405/6d7680587b3dd2d42390fcec08b01dd28c7a9456-1.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://am570.com.br/noticia.php?id=1854\",\r\n" +
    // " \"pageTitle\": \"Correio deve iniciar greve na próxima segunda-feira - Rádio Eldorado\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://am570.com.br/images/noticias/1854/1854.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://jornalentrevista.com.br/v4/verao-aracruz-2019-shows-de-humor-pagode-e-muito-forro-dao-o-tom-neste-fim-de-semana/\",\r\n"
    // +
    // " \"pageTitle\": \"Verão Aracruz 2019: Shows de humor, pagode e muito forró dão o ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://jornalentrevista.com.br/v4/wp-content/uploads/2019/09/d0e1fe80-20e0-0137-e556-6231c35b6685-minified-1.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://canaltech.com.br/mercado/dell-deve-vender-sua-divisao-de-softwares-por-us-2-bilhoes-70281/\",\r\n" +
    // " \"pageTitle\": \"Dell deve vender sua divisão de softwares por US$ 2 bilhões ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://timeline.canaltech.com.br/333499.400/sem-acordo-funcionarios-dos-correios-entram-em-greve-em-todo-o-brasil.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.androidpit.com.br/correios-problemas-roubos-incendios\",\r\n" +
    // " \"pageTitle\": \"O que está acontecendo com os \\u003cb\\u003eCorreios\\u003c/b\\u003e? | AndroidPIT\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://fscl01.fonpit.de/userfiles/6675138/image/29213734989002-w782.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://fscl01.fonpit.de/userfiles/6675138/image/29213734989002-w810h462.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://ndmais.com.br/noticias/correios-sobem-tarifas-em-7-5-e-carta-com-mais-de-meio-quilo-tera-preco-de-sedex/\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e sobem tarifas em 7,5% e carta com mais de meio quilo
    // terá ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://static.ndonline.com.br/2017/04/b2708673efd68531b69d1db217a42f9dda3a504f.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://ecommercenapratica.com/palestras-imperdiveis-mercado-livre-experience/\",\r\n" +
    // " \"pageTitle\": \"3 Palestras Imperdíveis no Mercado Livre Experience | Ecommerce ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://ecommercenapratica.com/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456-150x150.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.sideralfm.com.br/noticias/noticia/id:12465;crianca-da-entrada-na-upa-de-ouro-preto-com-suspeita-de-sarampo.html\",\r\n"
    // +
    // " \"pageTitle\": \"Criança dá entrada na UPA de Ouro Preto com ... - Sideral Fm\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.sideralfm.com.br/upload/noticia/12506/01_323182.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://leouve.com.br/antes-da-taca-brasil-acbf-goleia-na-ouro/amp/\",\r\n" +
    // " \"pageTitle\": \"Antes da Taça Brasil ACBF goleia na Ouro | Portal Leouve\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.leouve.com.br/2019/09/17143405/6d7680587b3dd2d42390fcec08b01dd28c7a9456-1.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://noticias.band.uol.com.br/amp/noticias/100000973456/correios-anunciam-reajuste-de-634-nas-tarifas.html\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e anunciam reajuste de 6,34% nas tarifas | Portal da
    // Band\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://imagem.band.com.br/f_464417.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://ndmais.com.br/noticias/agencias-comunitarias-dos-correios-serao-ampliadas-em-biguacu/\",\r\n" +
    // " \"pageTitle\": \"Agências comunitárias dos \\u003cb\\u003eCorreios\\u003c/b\\u003e serão ampliadas em Biguaçu
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://static.ndonline.com.br/2017/08/b90479c3a80ce61f3fae2e12500af4f5aff933de.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.sideralfm.com.br/noticias/noticia/id:12546;carteira-de-trabalho-digital-ja-esta-em-vigor.html\",\r\n"
    // +
    // " \"pageTitle\": \"Carteira de Trabalho Digital já está em vigor - www.sideralfm.com.br\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.sideralfm.com.br/upload/noticia/12506/01_323182.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://canaltech.com.br/mercado/Galaxy-S4-derrete-e-pega-fogo-embaixo-de-travesseiro/\",\r\n" +
    // " \"pageTitle\": \"Galaxy S4 derrete e pega fogo embaixo de travesseiro - Mercado\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://timeline.canaltech.com.br/333499.400/sem-acordo-funcionarios-dos-correios-entram-em-greve-em-todo-o-brasil.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://leouve.com.br/inicia-a-coleta-das-amostras-para-a-selecao-dos-melhores-vinhos-de-flores-da-cunha/amp/\",\r\n"
    // +
    // " \"pageTitle\": \"Inicia a coleta das amostras para a Seleção dos Melhores Vinhos ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.leouve.com.br/2019/09/12075205/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.portalviu.com.br/economia/page/21\",\r\n" +
    // " \"pageTitle\": \"Economia – Página: 21 – Portal Viu\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn-nuneshost.viuonline.com.br/wp-content/uploads/2018/03/Correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://leouve.com.br/jovem-e-agredido-em-tentativa-de-assalto-na-area-central-de-caxias-do-sul/\",\r\n" +
    // " \"pageTitle\": \"Jovem é agredido em tentativa de assalto na área central de Caxias ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.leouve.com.br/2019/09/12075205/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://noticias.r7.com/brasil/correios-abrem-plano-de-demissao-para-cortar-mais-de-5-mil-vagas-26112017\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e abrem plano de demissão para cortar mais de 5 mil
    // vagas ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://img.r7.com/images/2017/10/05/19ntkobrpp_97hnaeuw5c_file.jpg?dimensions=600x315&crop_position=c\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.r7.com/images/2017/10/05/19ntkobrpp_97hnaeuw5c_file.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://ilhotaonline.com.br/2019/09/19/funcionarios-dos-correios-suspendem-paralisacao-em-todo-o-pais/\",\r\n" +
    // " \"pageTitle\": \"Funcionários dos \\u003cb\\u003eCorreios\\u003c/b\\u003e suspendem paralisação em todo o país
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://ilhotaonline.com.br/wp-content/uploads/2019/09/323182.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.brasildefato.com.br/2019/04/24/correios-estao-na-esteira-das-privatizacoes-do-ministro-da-economia-paulo-guedes/\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e Estão Na Esteira Das Privatizações Do Ministro Da
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://farm66.staticflickr.com/65535/47629282642_032ea8ae72_b.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://farm66.staticflickr.com/65535/47629282642_032ea8ae72_z.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://canaltech.com.br/infra/evernote-leva-infraestrutura-de-dados-para-nuvem-do-google-80001/\",\r\n" +
    // " \"pageTitle\": \"Evernote leva infraestrutura de dados para nuvem do Google - Infra\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://timeline.canaltech.com.br/333499.400/sem-acordo-funcionarios-dos-correios-entram-em-greve-em-todo-o-brasil.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://ecommercenapratica.com/como-atingir-objetivos-e-metas/\",\r\n" +
    // " \"pageTitle\": \"Como atingir seus objetivos e metas em 2018 | Ecommerce na Prática\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://ecommercenapratica.com/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456-150x150.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.oantagonista.com/brasil/a-lista-das-17-estatais-que-serao-privatizadas/\",\r\n" +
    // " \"pageTitle\": \"A LISTA DAS 17 ESTATAIS QUE SERÃO PRIVATIZADAS - O ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.oantagonista.net/uploads/2019/06/29213734989002-5311235.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.oantagonista.net/uploads/2019/06/29213734989002-5311235-1024x682.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.frenet.com.br/blog/reajuste-de-precos-correios/\",\r\n" +
    // " \"pageTitle\": \"O Frenet já está preparado para o reajuste dos \\u003cb\\u003eCorreios\\u003c/b\\u003e •
    // Frenet ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.frenet.com.br/blog/wp-content/uploads/2018/03/correios-1024x410.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://n60.com.br/voce-ja-teve-uma-entrega-atrasada-descubra-como-evitar-este-inconveniente\",\r\n"
    // +
    // " \"pageTitle\": \"Você já teve uma entrega atrasada? Descubra como evitar este ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://s3.portalt5.com.br/imagens/correios-2.jpg?mtime=20171017070216\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.ecommercebrasil.com.br/noticias/correios-iniciam-estudos-para-abertura-de-capital-como-alternativa-a-privatizacao/\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e iniciam estudos para abertura de capital como
    // alternativa à ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://d3pwmv7nml69wv.cloudfront.net/wp-content/uploads/2018/02/09175200/Falta-de-funcion%C3%A1rios-gera-atraso-em-CTE-dos-Correios-de-S%C3%A3o-Paulo.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://jornalentrevista.com.br/v4/aras-diz-que-pais-precisa-combater-corrupcao-e-destravar-economia/\",\r\n" +
    // " \"pageTitle\": \"Aras diz que país precisa combater corrupção e destravar economia ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://jornalentrevista.com.br/v4/wp-content/uploads/2019/09/d0e1fe80-20e0-0137-e556-6231c35b6685-minified-1.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://leouve.com.br/amp/page/76/?option=com_giweather&view=smartloader&type=js&filename=giweather&mod_owner=module&mod_id=284&menu_Itemid=237\",\r\n"
    // +
    // " \"pageTitle\": \"Leouve é um portal de notícias da serra gaúcha, e ... - Portal Leouve\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.leouve.com.br/2019/09/17143405/6d7680587b3dd2d42390fcec08b01dd28c7a9456-1.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.sideralfm.com.br/noticias/noticia/id:12502;suspeitas-de-sarampo-em-ouro-preto-mg-sao-descartadas-apos-resultados-de-exames-da-funed.html\",\r\n"
    // +
    // " \"pageTitle\": \"Suspeitas de sarampo em Ouro Preto-MG são ... - Sideral Fm\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.sideralfm.com.br/upload/noticia/12506/01_323182.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://novonacamara.com.br/bancada-do-novo-defende-privatizacao-dos-correios-em-audiencia-publica/\",\r\n" +
    // " \"pageTitle\": \"Bancada do NOVO defende privatização dos \\u003cb\\u003eCorreios\\u003c/b\\u003e em
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://novonacamara.com.br/wp-content/uploads/2019/08/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://ecommercenapratica.com/vender-replicas-e-permitido/\",\r\n" +
    // " \"pageTitle\": \"Vender Réplicas é permitido? | Ecommerce na Prática\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://ecommercenapratica.com/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456-150x150.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.paiquere.com.br/tst-decide-que-empregados-dos-correios-devem-pagar-por-plano-de-saude/\",\r\n" +
    // " \"pageTitle\": \"TST decide que empregados dos \\u003cb\\u003eCorreios\\u003c/b\\u003e devem pagar por plano
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.paiquere.com.br/wp-content/uploads/2018/03/correios-150x150.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.paiquere.com.br/wp-content/uploads/2018/03/correios-960x640.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.paiquere.com.br/wp-content/uploads/2018/03/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://technanet.com.br/2019/08/09/privatizacao-dos-correios-e-criticada-por-trabalhadores-do-setor/\",\r\n" +
    // " \"pageTitle\": \"Privatização dos \\u003cb\\u003eCorreios\\u003c/b\\u003e é criticada por trabalhadores do
    // setor ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://technanet.com.br/wp-content/uploads/2019/08/privatizacao-dos-correios-e-criticada-por-trabalhadores-do-setor-1.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.conceicaoverdade.com.br/category/brasil/\",\r\n" +
    // " \"pageTitle\": \"Brasil – Portal Conceição Verdade\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.conceicaoverdade.com.br/wp-content/uploads/2019/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456-390x220.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.sideralfm.com.br/noticias/noticia/id:12558;guia-cachoeira-do-campo-realiza-a-acao-entre-amigos-para-a-sua-manutencao-no-ar.html\",\r\n"
    // +
    // " \"pageTitle\": \"Guia Cachoeira do Campo realiza a Ação Entre ... - Sideral Fm\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.sideralfm.com.br/upload/noticia/12506/01_323182.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.portalviu.com.br/author/redacao/page/196\",\r\n" +
    // " \"pageTitle\": \"Viu Online – Página: 196 – Portal Viu\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn-nuneshost.viuonline.com.br/wp-content/uploads/2018/03/Correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://gazetadocerrado.com.br/palavra/paralisacao/\",\r\n" +
    // " \"pageTitle\": \"Arquivos paralisação | Gazeta do Cerrado |\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://gazetadocerrado.com.br/wp-content/uploads/2018/02/CONCURSO-CORREIOS.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://jornalentrevista.com.br/v4/com-descontos-de-ate-70-campanha-promete-incentivar-o-consumo-e-estimular-a-economia/\",\r\n"
    // +
    // " \"pageTitle\": \"Com descontos de até 70%, campanha promete incentivar o ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://jornalentrevista.com.br/v4/wp-content/uploads/2019/09/d0e1fe80-20e0-0137-e556-6231c35b6685-minified-1.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.ecommercebrasil.com.br/noticias/bolsonaro-anuncia-demissao-presidente-correios/\",\r\n"
    // +
    // " \"pageTitle\": \"Bolsonaro anuncia demissão de presidente dos \\u003cb\\u003eCorreios\\u003c/b\\u003e\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://d3pwmv7nml69wv.cloudfront.net/wp-content/uploads/2018/02/09175200/Falta-de-funcion%C3%A1rios-gera-atraso-em-CTE-dos-Correios-de-S%C3%A3o-Paulo.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://br.financas.yahoo.com/noticias/privatiza%C3%A7%C3%A3o-dos-correios-%C3%A9-criticada-192000864.html\",\r\n"
    // +
    // " \"pageTitle\": \"Privatização dos \\u003cb\\u003eCorreios\\u003c/b\\u003e é criticada por trabalhadores do
    // setor\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://s.yimg.com/ny/api/res/1.2/o_PuFBDi8IAC7oiNzS1W6A--~A/YXBwaWQ9aGlnaGxhbmRlcjtzbT0xO3c9ODAw/https://media.zenfs.com/pt-br/canal_tech_990/8d688784b15879ba2d8c9f7f46892d66\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://tunisie-annonce.biz/collection/correios-fortaleza-br-116\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e Fortaleza Br 116 Tunisie Annonce\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://uploads.bemparana.com.br/upload/image/noticia/noticia_643528_img1_correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://leouve.com.br/amp/page/109/?option=com_giweather&view=smartloader&type=json&filename=giweather&mod_owner=module&mod_id=282&menu_Itemid=137\",\r\n"
    // +
    // " \"pageTitle\": \"Leouve é um portal de notícias da serra gaúcha, e ... - Portal Leouve\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.leouve.com.br/2019/09/12075205/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://novo.org.br/bancada-do-novo-defende-privatizacao-dos-correios-em-audiencia-publica/\",\r\n"
    // +
    // " \"pageTitle\": \"Bancada do NOVO defende privatização dos \\u003cb\\u003eCorreios\\u003c/b\\u003e em audiência
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://novo.org.br/wp-content/uploads/2019/08/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://gazetadocerrado.com.br/correios-reajusta-preco-do-servico-de-despacho-postal-veja-alteracao/\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e reajusta preço do serviço de despacho postal; veja
    // alteração\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://gazetadocerrado.com.br/wp-content/uploads/2018/02/CONCURSO-CORREIOS.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://gazetadocerrado.com.br/wp-content/uploads/2018/02/CONCURSO-CORREIOS-1024x682.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.androidpit.com.br/dicas-para-ter-menos-dor-de-cabeca-com-importacao\",\r\n" +
    // " \"pageTitle\": \"Seis dicas para ter menos dor de cabeça com importação | AndroidPIT\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://fscl01.fonpit.de/userfiles/6675138/image/29213734989002-w782.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://fscl01.fonpit.de/userfiles/6675138/image/29213734989002-w810h462.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://ecommercenapratica.com/como-usar-seo-para-melhorar-anuncios-no-google-ads/\",\r\n" +
    // " \"pageTitle\": \"Como usar SEO para melhorar anúncios no Google Ads ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://ecommercenapratica.com/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456-150x150.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://comerciariosdesousa.com.br/2018/05/17/mesmo-com-lucro-empresa-segue-com-demissoes-e-fechamento-de-agencias/\",\r\n"
    // +
    // " \"pageTitle\": \"Mesmo com lucro, empresa segue com demissões e fechamento de ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://comerciariosdesousa.com.br/wp-content/uploads/2018/05/323183.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://noticias.band.uol.com.br/noticias/100000973456/correios-anunciam-reajuste-de-634-nas-tarifas.html\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e anunciam reajuste de 6,34% nas tarifas - Notícias -
    // Uol\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://imagem.band.com.br/f_464417.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.cidadelivre.org.br/index.php/pt/todas-as-noticias-publicadas/10-politica/1627-correios-estao-na-esteira-das-privatizacoes-do-ministro-da-economia-paulo-guedes\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e estão na esteira das privatizações do ministro da
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://farm66.staticflickr.com/65535/47629282642_032ea8ae72_z.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.gazetaonline.com.br/cbn_vitoria/reportagens/2018/10/fechamento-de-agencias-dos-correios-tambem-atinge-o-es-1014152408.html\",\r\n"
    // +
    // " \"pageTitle\": \"Fechamento de agências dos \\u003cb\\u003eCorreios\\u003c/b\\u003e também atinge o ES
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://midias.gazetaonline.com.br/_midias/jpg/2017/10/04/29213734989002-5311235.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://midias.gazetaonline.com.br/_midias/jpg/2017/10/04/605x330/1_29213734989002-5311235.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://midias.gazetaonline.com.br/_midias/jpg/2017/10/04/290x330/1_29213734989002-5311235.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.bemparana.com.br/noticia/policia-federal-cumpre-7-mandados-e-prende-3-funcionarios-dos-correios-no-pr\",\r\n"
    // +
    // " \"pageTitle\": \"Polícia Federal cumpre 7 mandados e prende 3 funcionários dos ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://uploads.bemparana.com.br/upload/image/noticia/noticia_570111_img1_correios-2.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.youtube.com/watch?v=8jFLP1y6kTs\",\r\n" +
    // " \"pageTitle\": \"Privatização dos \\u003cb\\u003eCorreios\\u003c/b\\u003e - Sim ou Não? - YouTube\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://i.ytimg.com/vi/8jFLP1y6kTs/hqdefault.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.ecommercebrasil.com.br/noticias/falta-de-funcionarios-gera-atraso-em-cte-dos-correios-de-sao-paulo/\",\r\n"
    // +
    // " \"pageTitle\": \"Falta de funcionários gera atraso em CTE dos \\u003cb\\u003eCorreios\\u003c/b\\u003e de São
    // Paulo\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://d3pwmv7nml69wv.cloudfront.net/wp-content/uploads/2018/02/09175200/Falta-de-funcion%C3%A1rios-gera-atraso-em-CTE-dos-Correios-de-S%C3%A3o-Paulo.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.sul21.com.br/ultimas-noticias/politica/2019/04/correios-estao-na-esteira-das-privatizacoes-do-ministro-da-economia-paulo-guedes/\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e estão na esteira das privatizações do ministro da ...
    // - Sul21\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.sul21.com.br/wp-content/uploads/2019/04/20190424-correios_div.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://jrs.digital/2017/11/06/correios-anuncia-licitacao-para-seguro-rc/\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e anunciam licitação para Seguro RC – JRS\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://jrs.digital/wp-content/uploads/2017/11/CONCURSO-CORREIOS.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.ibahia.com/brasil/detalhe/noticia/correios-passam-a-oferecer-servico-de-interrupcao-de-entrega-entenda/\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e passam a oferecer serviço de interrupção de entrega -
    // iBahia\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.ibahia.com/fileadmin/user_upload/ibahia/2019/maio/06/correios1.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://ibahia-cdn2.cworks.cloud/fileadmin/user_upload/ibahia/2019/maio/06/correios.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.ibahia.com/fileadmin/user_upload/ibahia/2019/maio/06/correios1.jpg?width=1200&enable=upscale\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://canaltech.com.br/apps/uber-e-processado-nos-estados-unidos-por-manipulacao-de-precos-61433/\",\r\n" +
    // " \"pageTitle\": \"Uber é processado nos Estados Unidos por suposta manipulação ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://timeline.canaltech.com.br/333499.400/sem-acordo-funcionarios-dos-correios-entram-em-greve-em-todo-o-brasil.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.paiquere.com.br/sem-correios-moradores-de-distritos-retiram-correspondencias-em-escolas-municipais/\",\r\n"
    // +
    // " \"pageTitle\": \"Sem \\u003cb\\u003eCorreios\\u003c/b\\u003e, moradores de distritos retiram correspondências
    // em ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.paiquere.com.br/wp-content/uploads/2018/03/correios-150x150.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.paiquere.com.br/wp-content/uploads/2018/03/correios-960x640.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.paiquere.com.br/wp-content/uploads/2018/03/correios-1024x682.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.paiquere.com.br/wp-content/uploads/2018/03/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.folhavitoria.com.br/geral/noticia/05/2019/agencia-dos-correios-em-vitoria-tem-19-funcionarios-contaminados-com-sintomas-de-gastroenterite\",\r\n"
    // +
    // " \"pageTitle\": \"Agência dos \\u003cb\\u003eCorreios\\u003c/b\\u003e em Vitória tem 19 funcionários
    // contaminados ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://assets.folhavitoria.com.br/images/d0e1fe80-20e0-0137-e556-6231c35b6685--minified.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.sideralfm.com.br/noticias/noticia/id:12675;mesmo-sem-horario-de-verao-celulares-adiantam-uma-hora-automaticamente.html\",\r\n"
    // +
    // " \"pageTitle\": \"Mesmo sem horário de verão, celulares adiantam uma ... - Sideral Fm\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.sideralfm.com.br/upload/noticia/12506/01_323182.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.tecmundo.com.br/mercado/122550-correios-anuncia-encerramento-banco-postal-1-8-mil-agencias.htm\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e anuncia encerramento do Banco Postal em mais de 1,8
    // mil ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2017/09/29/29213734989002.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.folhavitoria.com.br/geral/noticia/05/2019/correios-vao-fechar-sete-unidades-no-es-cinco-delas-na-grande-vitoria\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e vão fechar sete unidades no ES; cinco delas na Grande
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://assets.folhavitoria.com.br/images/d0e1fe80-20e0-0137-e556-6231c35b6685--minified.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.ipatiua.com.br/entrega-e-rastreamento-dos-correios/\",\r\n" +
    // " \"pageTitle\": \"Entrega e rastreamento dos \\u003cb\\u003ecorreios\\u003c/b\\u003e - Ipatiua\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.ipatiua.com.br/wp-content/uploads/2018/03/Falta-de-funcion%C3%A1rios-gera-atraso-em-CTE-dos-Correios-de-S%C3%A3o-Paulo.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.oantagonista.com/brasil/presidente-dos-correios-muda-quase-toda-a-diretoria/\",\r\n" +
    // " \"pageTitle\": \"Presidente dos \\u003cb\\u003eCorreios\\u003c/b\\u003e muda quase toda a diretoria - O
    // Antagonista\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.oantagonista.net/uploads/2019/06/29213734989002-5311235.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.rastrearobjetos.com.br/blog/tags/efetiv\",\r\n" +
    // " \"pageTitle\": \"efetiv – Rastrear \\u003cb\\u003eCorreios\\u003c/b\\u003e\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.rastrearobjetos.com.br/blog/wp-content/uploads/2017/09/caixa-correios-amarela-e-azul-300x200.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://imirante.com/sao-luis/noticias/2019/10/14/criminosos-tentam-assaltar-agencia-dos-correios-na-avenida-dos-holandeses.shtml\",\r\n"
    // +
    // " \"pageTitle\": \"Criminosos tentam assaltar agência dos \\u003cb\\u003eCorreios\\u003c/b\\u003e na Avenida dos
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://imirante.com/imagens/2019/03/28/1553786531-485758118-810x471.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.gazetaonline.com.br/noticias/cidades/2019/05/funcionarios-dos-correios-sao-afastados-com-sintomas-de-gastroenterite-1014182570.html\",\r\n"
    // +
    // " \"pageTitle\": \"Funcionários dos \\u003cb\\u003eCorreios\\u003c/b\\u003e são afastados com sintomas de
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://midias.gazetaonline.com.br/_midias/jpg/2017/10/04/29213734989002-5311235.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://midias.gazetaonline.com.br/_midias/jpg/2017/10/04/605x330/1_29213734989002-5311235.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.migalhas.com.br/Quentes/17,MI296288,81042-Tribunal+do+Cade+aprova+criacao+de+joint+venture+entre+Azul+e+Correios\",\r\n"
    // +
    // " \"pageTitle\": \"Tribunal do Cade aprova criação de joint venture entre Azul e ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.globalframe.com.br/gf_base/empresas/MIGA/imagens/47C47FFC5B8CED4BCC82045F0B29F5D0DB1A_correiosazul.png\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.folhavitoria.com.br/policia/noticia/03/2019/justica-decreta-prisao-preventiva-de-tres-funcionarios-dos-correios\",\r\n"
    // +
    // " \"pageTitle\": \"Justiça decreta prisão preventiva de três funcionários dos
    // \\u003cb\\u003eCorreios\\u003c/b\\u003e\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://assets.folhavitoria.com.br/images/d0e1fe80-20e0-0137-e556-6231c35b6685--minified.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.tecmundo.com.br/mercado/124350-correios-acusam-comercio-chines-burlar-lei-envio-encomendas.htm\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e acusam comércio chinês de burlar a lei para envio de
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2017/11/21/21095110525030.jpg?w=1120&h=420&mode=crop&scale=both\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2017/11/21/21095110525030.jpg?w=352&h=208&mode=crop&scale=both\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.ibxk.com.br/2017/11/21/21095137556032.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://noticias.reclameaqui.com.br/noticias/correios-reclamacoes-de-atraso-na-entrega-aumentaram-28-em-2_3148/\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e: reclamações de atraso na entrega aumentaram 28% em
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://noticias.reclameaqui.com.br/uploads/403348113.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.causaoperaria.org.br/cinismo-istoe-golpista-ataca-pt-para-fazer-propaganda-pela-privatizacao-dos-correios/\",\r\n"
    // +
    // " \"pageTitle\": \"Cinismo: IstoÉ golpista ataca PT para fazer propaganda pela ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.causaoperaria.org.br/wp-content/uploads/2019/03/correios-privatizado-2-1024x585.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://ecommercenapratica.com/como-anunciar-no-facebook-ads-2a-parte-do-manual/\",\r\n" +
    // " \"pageTitle\": \"Como Anunciar no Facebook Ads - Ecommerce na Prática\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://ecommercenapratica.com/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456-150x150.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.oantagonista.com/economia/guedes-qual-e-a-duvida-em-privatizar-os-correios/\",\r\n" +
    // " \"pageTitle\": \"Guedes: &quot;Qual é a dúvida em privatizar os \\u003cb\\u003eCorreios\\u003c/b\\u003e?&quot;
    // - O Antagonista\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.oantagonista.net/uploads/2019/06/29213734989002-5311235.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.ecommercebrasil.com.br/noticias/presidente-dos-correios-fala-de-fechamento-de-agencias-e-investimento-online/\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e: fechamento de agências e investimento em
    // tecnologia\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://d3pwmv7nml69wv.cloudfront.net/wp-content/uploads/2018/02/09175200/Falta-de-funcion%C3%A1rios-gera-atraso-em-CTE-dos-Correios-de-S%C3%A3o-Paulo.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.folhavitoria.com.br/geral/noticia/10/2018/agencia-dos-correios-de-cariacica-tem-atividades-encerradas\",\r\n"
    // +
    // " \"pageTitle\": \"Agência dos \\u003cb\\u003eCorreios\\u003c/b\\u003e de Cariacica tem atividades
    // encerradas\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://assets.folhavitoria.com.br/images/2be99f50-b372-0136-99aa-6231c35b6685--minified.png\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.rioverdeagora.com.br/colunas/cidade/post/correios-negociacao-com-trabalhadores-continua-e-prestacao-de-servicos-esta-normal\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e: negociação com trabalhadores ... - Rio Verde
    // Agora\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.rioverdeagora.com.br/uploads/noticias/3b7071c9c2db480a89575bf2e09778e0_1564658418.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.folhavitoria.com.br/geral/noticia/06/2019/correios-lancam-selos-com-o-tema-fungos-em-comemoracao-ao-dia-mundial-do-meio-ambiente\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e lançam selos com o tema fungos em comemoração ao Dia
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://assets.folhavitoria.com.br/images/d0e1fe80-20e0-0137-e556-6231c35b6685--minified.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://jornalsomos.com.br/rio-verde/detalhe/correios-fecha-nove-agencias-em-goias-uma-delas-em-rio-verde\",\r\n"
    // +
    // " \"pageTitle\": \"Correios fecha nove agências em Goiás, uma delas ... - Jornal Somos\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://jornalsomos.com.br/imgc/noticias/600x350/correios-fecham-nove-agencias-em-goias-ate-inicio-de-julho-veja-lista-1-e1559129994659.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://lindenmeyer.adv.br/responsabilidade-civil-pela-ma-prestacao-de-servico-dos-correios/\",\r\n"
    // +
    // " \"pageTitle\": \"a responsabilidade civil pela má prestação de serviço dos
    // \\u003cb\\u003ecorreios\\u003c/b\\u003e\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://lindenmeyer.adv.br/wp-content/uploads/2018/05/11124032127002.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.folhavitoria.com.br/geral/noticia/05/2019/mpf-quer-aumento-da-pena-para-funcionario-dos-correios-que-furtou-mercadorias-em-vila-velha\",\r\n"
    // +
    // " \"pageTitle\": \"MPF quer aumento da pena para funcionário dos \\u003cb\\u003eCorreios\\u003c/b\\u003e que
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://assets.folhavitoria.com.br/images/d0e1fe80-20e0-0137-e556-6231c35b6685--minified.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://coyotecriativo.com/correios-tem-novo-reajuste-nas-tarifas/\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e tem novo reajuste nas tarifas - Coyote
    // Criativo\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://coyotecriativo.com/wp-content/uploads/2018/11/correios.png\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://contec.org.br/correios-lancam-3o-plano-de-demissao-em-2017-para-tentar-zerar-prejuizo/\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e lançam 3º plano de demissão em 2017 para tentar zerar
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://contec.org.br/wp-content/uploads/2017/09/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.causaoperaria.org.br/nao-ao-pdv-dos-correios/\",\r\n" +
    // " \"pageTitle\": \"Não ao PDV dos \\u003cb\\u003eCorreios\\u003c/b\\u003e! - Diário Causa Operária\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.causaoperaria.org.br/wp-content/uploads/2019/05/correios-privatizado-2-1-1024x585.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.ecommercebrasil.com.br/noticias/lojas-poderao-ter-agencias-modulares-dos-correios-em-seu-estabelecimento/\",\r\n"
    // +
    // " \"pageTitle\": \"Lojas poderão ter agências modulares dos \\u003cb\\u003eCorreios\\u003c/b\\u003e | E-Commerce
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://d3pwmv7nml69wv.cloudfront.net/wp-content/uploads/2018/02/09175200/Falta-de-funcion%C3%A1rios-gera-atraso-em-CTE-dos-Correios-de-S%C3%A3o-Paulo.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://leouve.com.br/amp/page/86/?option=com_giweather&view=smartloader&type=json&filename=giweather&mod_owner=module&mod_id=279&menu_Itemid=115\",\r\n"
    // +
    // " \"pageTitle\": \"Leouve é um portal de notícias da serra gaúcha, e ... - Portal Leouve\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.leouve.com.br/2019/09/12075205/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://jornalentrevista.com.br/v4/tempo-permanece-firme-em-todo-espirito-santo-veja-a-previsao/\",\r\n" +
    // " \"pageTitle\": \"Tempo permanece firme em todo Espírito Santo. Veja a previsão ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://jornalentrevista.com.br/v4/wp-content/uploads/2019/09/d0e1fe80-20e0-0137-e556-6231c35b6685-minified-1.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.causaoperaria.org.br/bolsonaro-reafirma-vai-entregar-eletrobras-e-correios-para-os-patroes/\",\r\n"
    // +
    // " \"pageTitle\": \"Bolsonaro reafirma: vai entregar Eletrobras e \\u003cb\\u003eCorreios\\u003c/b\\u003e para os
    // patrões\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.causaoperaria.org.br/wp-content/uploads/2019/05/correios-privatizado-2-1-1024x585.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://noticiahoje.net/governo-quer-privatizar-correios/\",\r\n" +
    // " \"pageTitle\": \"Governo quer privatizar \\u003cb\\u003eCorreios\\u003c/b\\u003e - Notícia Hoje\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://noticiahoje.net/wp-content/uploads/2019/04/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.paiquere.com.br/author/weslley/page/40/\",\r\n" +
    // " \"pageTitle\": \"Weslley Lemos, Author at Portal Paiquerê - Página 40 de 522\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.paiquere.com.br/wp-content/uploads/2018/03/correios-960x640.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.portalviu.com.br/page/226?s&PageSpeed=noscript\",\r\n" +
    // " \"pageTitle\": \"Resultados da pesquisa por “” – Página: 226 – Portal Viu\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.portalviu.com.br/wp-content/uploads/2018/03/Correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.acidadeon.com/araraquara/cotidiano/regiao/NOT,0,0,1411783,homens+armados+levaram+r+6+5+mil+de+agencia+dos+correios.aspx\",\r\n"
    // +
    // " \"pageTitle\": \"Homens armados levaram R$ 6,5 mil de agência dos \\u003cb\\u003eCorreios\\u003c/b\\u003e
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://emc.acidadeon.com/dbimagens/agencia_dos_790x505_22032019081710.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://varelanoticias.com.br/ivete-sangalo-bebeto-sao-condecorados-em-brasilia/\",\r\n" +
    // " \"pageTitle\": \"Presidente da Record e Ivete Sangalo são condecorados em ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://varelanoticias.com.br/wp-content/uploads/2019/09/Correios-360x210.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.alerj.rj.gov.br/Visualizar/Noticia/47158\",\r\n" +
    // " \"pageTitle\": \"documento de veículo deverá ser enviado pelos \\u003cb\\u003ecorreios\\u003c/b\\u003e sem ...
    // - Alerj\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.alerj.rj.gov.br/Uploads/Publicacao/Imagem/media_03102019_122956correios-750x410.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://varelanoticias.com.br/presidente-do-tst-desiste-de-proibir-decotes-e-camisetas-no-tribunal/\",\r\n" +
    // " \"pageTitle\": \"Presidente do TST desiste de proibir decotes e camisetas no ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://varelanoticias.com.br/wp-content/uploads/2019/09/Correios-360x210.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.tudocelular.com/mercado/noticias/n138849/pf-prende-funcionarios-correios-roubo-encomendas.html\",\r\n"
    // +
    // " \"pageTitle\": \"Encomenda demorando? PF prende funcionários dos \\u003cb\\u003eCorreios\\u003c/b\\u003e
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://t.tudocdn.net/323182?w=646&h=284\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://jmonline.com.br/novo/?noticias,2,CIDADE,179576\",\r\n" +
    // " \"pageTitle\": \"Duas agências dos \\u003cb\\u003eCorreios\\u003c/b\\u003e em Uberaba serão fechadas até 5 de
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://jmonline.com.br/uploads/noticia/179576_1.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.gazetaonline.com.br/noticias/policia/2019/01/bandidos-arrombam-cofre-em-agencia-dos-correios-de-alegre-1014165528.html\",\r\n"
    // +
    // " \"pageTitle\": \"Bandidos arrombam cofre em agência dos \\u003cb\\u003eCorreios\\u003c/b\\u003e de Alegre | A
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://midias.gazetaonline.com.br/_midias/jpg/2017/10/04/29213734989002-5311235.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://jornalentrevista.com.br/v4/mais-de-30-condutores-sao-autuados-por-embriaguez-e-recusa-ao-bafometro-na-grande-vitoria/\",\r\n"
    // +
    // " \"pageTitle\": \"Mais de 30 condutores são autuados por embriaguez e recusa ao ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://jornalentrevista.com.br/v4/wp-content/uploads/2019/09/d0e1fe80-20e0-0137-e556-6231c35b6685-minified-1.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.folhavitoria.com.br/economia/noticia/06/2019/porta-voz-bolsonaro-nao-tem-ainda-data-da-demissao-do-presidente-dos-correios\",\r\n"
    // +
    // " \"pageTitle\": \"Porta-voz: presidente não tem ainda data da demissão do ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://assets.folhavitoria.com.br/images/d0e1fe80-20e0-0137-e556-6231c35b6685--minified.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://imirante.com/codo/noticias/2019/03/28/em-codo-bandidos-levam-todo-o-dinheiro-de-agencia-dos-correios.shtml\",\r\n"
    // +
    // " \"pageTitle\": \"Em Codó, bandidos levam todo o dinheiro de agência dos \\u003cb\\u003eCorreios\\u003c/b\\u003e
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://imirante.com/imagens/2019/03/28/1553786531-485758118-810x471.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.jmonline.com.br/novo/?noticias,2,CIDADE,179576\",\r\n" +
    // " \"pageTitle\": \"Duas agências dos \\u003cb\\u003eCorreios\\u003c/b\\u003e em Uberaba serão fechadas até 5 de
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.jmonline.com.br/uploads/noticia/179576_1.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://jornalatual.com.br/2019/10/03/crlv-devera-ser-enviado-pelos-correios-sem-custo-adicional/\",\r\n" +
    // " \"pageTitle\": \"CRLV deverá ser enviado pelos \\u003cb\\u003ecorreios\\u003c/b\\u003e sem custo adicional
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://jornalatual.com.br/wp-content/uploads/2019/10/SERVI%C3%87O-CRLV-dever%C3%A1-ser-enviado-pelos-correios-sem-custo-adicional.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://ndmais.com.br/noticias/correios-prorrogam-inscricoes-para-vagas-de-estagio-em-sao-jose/\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e prorrogam inscrições para vagas de estágio em São José
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://static.ndonline.com.br/2017/04/b2708673efd68531b69d1db217a42f9dda3a504f.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://noticias.r7.com/cidades/folha-vitoria/agencia-dos-correios-em-vitoria-tem-19-funcionarios-contaminados-com-sintomas-de-gastroenterite-24052019\",\r\n"
    // +
    // " \"pageTitle\": \"Agência dos \\u003cb\\u003eCorreios\\u003c/b\\u003e em Vitória tem 19 funcionários
    // contaminados ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.r7.com/images/folha-vitoria-24052019131200330\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.r7.com/images/folha-vitoria-24052019131200330?dimensions=600x315&crop_position=c\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://img.r7.com/images/folha-vitoria-24052019131200330?dimensions=660x360\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://jornalentrevista.com.br/v4/acao-conjunta-realizada-pelo-5o-e-13o-batalhao-resulta-na-apreensao-de-arma-e-drogas-no-municipio-de-aracruz/\",\r\n"
    // +
    // " \"pageTitle\": \"AÇÃO CONJUNTA REALIZADA PELO 5º E 13º BATALHÃO ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://jornalentrevista.com.br/v4/wp-content/uploads/2019/09/d0e1fe80-20e0-0137-e556-6231c35b6685-minified-1.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://canaltech.com.br/utilitarios/correios-lanca-nova-ferramenta-que-permite-consultar-encomendas-via-cpf-ou-cnpj-107158/\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e lança nova ferramenta que permite consultar encomendas
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://timeline.canaltech.com.br/269256.700/correios-lanca-nova-ferramenta-que-permite-consultar-encomendas-via-cpf-ou-cnpj-107158.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.folhavitoria.com.br/economia/noticia/08/2019/salim-mattar-modelo-de-privatizacao-dos-correios-ficara-mais-claro-em-2020\",\r\n"
    // +
    // " \"pageTitle\": \"Salim Mattar: Modelo de privatização dos \\u003cb\\u003eCorreios\\u003c/b\\u003e ficará mais
    // claro ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://assets.folhavitoria.com.br/images/d0e1fe80-20e0-0137-e556-6231c35b6685--minified.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://jmonline.com.br/novo/?noticias,1,GERAL,184187\",\r\n" +
    // " \"pageTitle\": \"Governo anuncia privatização dos \\u003cb\\u003eCorreios\\u003c/b\\u003e nesta quarta - Jornal
    // da ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://jmonline.com.br/uploads/noticia/184187_1.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.folhavitoria.com.br/economia/noticia/08/2019/privatizacao-dos-correios-nao-esta-em-andamento-diz-ministro-marcos-pontes\",\r\n"
    // +
    // " \"pageTitle\": \"Privatização dos \\u003cb\\u003eCorreios\\u003c/b\\u003e não está em andamento, diz ministro
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://assets.folhavitoria.com.br/images/d0e1fe80-20e0-0137-e556-6231c35b6685--minified.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://br.advfn.com/jornal/2019/03/correios-planejam-entrar-na-briga-com-uber-rappi-e-ifood\",\r\n"
    // +
    // " \"pageTitle\": \"Correios planejam entrar na briga com Uber, Rappi ... - ADVFN News\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://br.advfn.com/jornal/files/2019/03/correios-2-585x390.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.portalviu.com.br/page/230?s\",\r\n" +
    // " \"pageTitle\": \"Resultados da pesquisa por “” – Página: 230 – Portal Viu\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.portalviu.com.br/wp-content/uploads/2018/03/Correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.folhavitoria.com.br/policia/noticia/09/2019/homens-armados-assaltam-agencia-dos-correios-em-vila-velha\",\r\n"
    // +
    // " \"pageTitle\": \"Homens armados assaltam agência dos \\u003cb\\u003eCorreios\\u003c/b\\u003e em Vila
    // Velha\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://assets.folhavitoria.com.br/images/d0e1fe80-20e0-0137-e556-6231c35b6685--minified.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.oantagonista.com/brasil/enquanto-a-privatizacao-nao-vem/\",\r\n" +
    // " \"pageTitle\": \"Enquanto a privatização não vem... - O Antagonista\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.oantagonista.net/uploads/2019/06/29213734989002-5311235.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://tvbarroso.com.br/category/destaque/page/154/?filter_by=featured\",\r\n" +
    // " \"pageTitle\": \"Arquivos Destaque - Página 154 de 206 - TV Barroso\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://tvbarroso.com.br/wp-content/uploads/2018/02/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.opopularjm.com.br/bolsonaro-afirma-que-correios-serao-privatizados/\",\r\n" +
    // " \"pageTitle\": \"Bolsonaro afirma que \\u003cb\\u003eCorreios\\u003c/b\\u003e serão privatizados | O
    // Popular\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.opopularjm.com.br/wp-content/uploads/2017/09/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.odiariocarioca.com/noticia-2019-10-05-agora-e-lei-documento-de-veiculo-devera-ser-enviado-pelos-correios-sem-custo-adicional-9670622.carioca.html\",\r\n"
    // +
    // " \"pageTitle\": \"documento de veículo deverá ser enviado pelos \\u003cb\\u003ecorreios\\u003c/b\\u003e sem
    // custo\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.odiariocarioca.com/wp-content/uploads/2019/10/unnamed-7.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.gazetaonline.com.br/noticias/norte/2018/05/agencia-dos-correios-e-arrombada-em-marilandia-1014130208.html\",\r\n"
    // +
    // " \"pageTitle\": \"Agência dos \\u003cb\\u003eCorreios\\u003c/b\\u003e é arrombada em Marilândia | A
    // Gazeta\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://midias.gazetaonline.com.br/_midias/jpg/2017/10/04/29213734989002-5311235.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.androidpit.com.br/privatizacao-dos-correios-bom-ou-ruim\",\r\n" +
    // " \"pageTitle\": \"Devemos achar que a privatização dos \\u003cb\\u003eCorreios\\u003c/b\\u003e é algo bom ou
    // ruim ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://fscl01.fonpit.de/userfiles/6675138/image/29213734989002-w782.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://fscl01.fonpit.de/userfiles/6675138/image/29213734989002-w810h462.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.osaogoncalo.com.br/servicos/63671/documento-de-veiculo-devera-ser-enviado-pelos-correios-sem-custo-adicional\",\r\n"
    // +
    // " \"pageTitle\": \"Documento de veículo deverá ser enviado pelos \\u003cb\\u003ecorreios\\u003c/b\\u003e sem
    // custo ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.osaogoncalo.com.br/img/normal/60000/otacilio-barbosa_00063671_0.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://dotcon.com.br/blog/e-commerce-entenda-nova-regra-dos-correios-para-envio-de-mercadorias/\",\r\n" +
    // " \"pageTitle\": \"E-commerce: entenda a nova regra dos \\u003cb\\u003eCorreios\\u003c/b\\u003e para envio de
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://dotcon.com.br/wp-content/uploads/2018/01/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.gazetaonline.com.br/noticias/economia/2018/06/correios-lancam-aplicativo-que-permite-pre-postagem-de-encomendas-1014134679.html\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e lançam aplicativo que permite pré-postagem de ... - A
    // Gazeta\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://midias.gazetaonline.com.br/_midias/jpg/2017/10/04/29213734989002-5311235.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.folhavitoria.com.br/economia/noticia/08/2019/privatizacoes-anunciadas-por-guedes-comecarao-com-correios-diz-bolsonaro\",\r\n"
    // +
    // " \"pageTitle\": \"Privatizações anunciadas por Guedes começarão com \\u003cb\\u003eCorreios\\u003c/b\\u003e, diz
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://assets.folhavitoria.com.br/images/d0e1fe80-20e0-0137-e556-6231c35b6685--minified.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://jmonline.com.br/novo/?noticias,2,cidade,157668\",\r\n" +
    // " \"pageTitle\": \"Justiça do Trabalho defere liminar para que \\u003cb\\u003eCorreios\\u003c/b\\u003e conceda
    // férias ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://jmonline.com.br/uploads/noticia/157668_2.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.gazetaonline.com.br/noticias/cidades/2018/06/correios-vao-fazer-mutirao-para-normalizar-distribuicao-de-encomendas-1014134610.html\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e vão fazer mutirão para normalizar distribuição de
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://midias.gazetaonline.com.br/_midias/jpg/2017/10/04/29213734989002-5311235.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.opopularjm.com.br/correios-suspende-taxa-extra-para-cobrir-seguranca-privada/\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e suspende taxa extra para cobrir segurança privada | O
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.opopularjm.com.br/wp-content/uploads/2017/09/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.bemparana.com.br/noticia/bolsonaro-autoriza-estudo-para-privatizacao-dos-correios\",\r\n" +
    // " \"pageTitle\": \"Bolsonaro autoriza estudo para privatização dos \\u003cb\\u003eCorreios\\u003c/b\\u003e - Bem
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://uploads.bemparana.com.br/upload/image/noticia/noticia_643528_img1_correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://oimparcial.com.br/cidades/2018/12/pf-do-maranhao-desmancha-organizacao-criminosa-que-atuava-nos-correios/\",\r\n"
    // +
    // " \"pageTitle\": \"PF do Maranhão desmancha organização criminosa que atuava ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://oimparcial.com.br/app/uploads/2018/08/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.ibahia.com/brasil/detalhe/noticia/correios-lanca-aplicativo-que-agiliza-atendimento/\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e lança aplicativo que agiliza atendimento -
    // iBahia\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.ibahia.com/fileadmin/user_upload/ibahia/2018/junho/06/correios1.jpg?width=1200&enable=upscale\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://jmonline.com.br/novo/?cadernos,2,CIDADE,25/04/2018\",\r\n" +
    // " \"pageTitle\": \"cidade - :: Jornal da Manhã :: [47 anos]\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://jmonline.com.br/uploads/noticia/157668_2.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://grnews.com.br/28072019/mundo-fama/carol-csan-lanca-clipe-de-to-facim-assista\",\r\n" +
    // " \"pageTitle\": \"Carol Csan lança clipe de “Tô Facim”. Assista – Portal GRNEWS\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i2.wp.com/grnews.com.br/wp-content/uploads/2017/08/correios.jpg?resize=455%2C250&ssl=1\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://jmonline.com.br/novo/?noticias,5,,151260\",\r\n" +
    // " \"pageTitle\": \"Justiça Federal condena assaltantes de agência dos \\u003cb\\u003eCorreios\\u003c/b\\u003e em
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://jmonline.com.br/uploads/noticia/151260_1.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://br.advfn.com/jornal/2019/05/correios-passam-a-oferecer-novos-servicos-com-o-balcao-do-cidadao\",\r\n" +
    // " \"pageTitle\": \"Correios passam a oferecer novos serviços com o ... - ADVFN News\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://br.advfn.com/jornal/files/2019/03/correios-2-585x390.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.folhavitoria.com.br/policia/noticia/09/2019/pf-desmonta-fraudes-de-r-13-milhoes-nos-correios\",\r\n"
    // +
    // " \"pageTitle\": \"PF desmonta fraudes de R$ 13 milhões nos \\u003cb\\u003eCorreios\\u003c/b\\u003e na Região
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://assets.folhavitoria.com.br/images/d0e1fe80-20e0-0137-e556-6231c35b6685--minified.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://oimparcial.com.br/cidades/2018/08/correios-cobram-nova-taxa-em-todas-as-encomendas-internacionais/\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e cobram nova taxa em todas as encomendas internacionais
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://oimparcial.com.br/app/uploads/2018/08/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.folhavitoria.com.br/economia/noticia/10/2019/governo-inclui-correios-e-telebras-no-ppi-para-estudos-de-parcerias\",\r\n"
    // +
    // " \"pageTitle\": \"Governo inclui \\u003cb\\u003eCorreios\\u003c/b\\u003e e Telebras no PPI para estudos de
    // parcerias\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://assets.folhavitoria.com.br/images/d0e1fe80-20e0-0137-e556-6231c35b6685--minified.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.bemparana.com.br/noticia/policia-federal-cumpre-7-mandados-e-prende-3-funcionarios-dos-correios-no-pr?fb_comment_id=2352901048067987_2353335491357876\",\r\n"
    // +
    // " \"pageTitle\": \"Polícia Federal cumpre 7 mandados e prende 3 funcionários dos ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://uploads.bemparana.com.br/upload/image/noticia/noticia_570111_img1_correios-2.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://leouve.com.br/governo-federal-abre-estudos-para-privatizar-correios-e-mais-oito-estatais/\",\r\n" +
    // " \"pageTitle\": \"Governo Federal abre estudos para privatizar \\u003cb\\u003eCorreios\\u003c/b\\u003e e mais
    // oito ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.leouve.com.br/2019/08/22081629/6d7680587b3dd2d42390fcec08b01dd28c7a9456.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://br.noticias.yahoo.com/correios-bolsonaro-cede-e-pode-123300810.html\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e | Bolsonaro cede e pode permitir privatização da
    // estatal\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://s.yimg.com/uu/api/res/1.2/Fa3YAwdoWUs9SgQSx3OUow--~B/aD03MjA7dz0xMjgwO3NtPTE7YXBwaWQ9eXRhY2h5b24-/https://media.zenfs.com/pt-br/canal_tech_990/f3623f01efc419093ebc20a3c7b47058\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://s.yimg.com/ny/api/res/1.2/2bqZP23INrIn5pDd.AgRBw--/YXBwaWQ9aGlnaGxhbmRlcjt3PTEyODA7aD03MjA-/https://s.yimg.com/uu/api/res/1.2/Fa3YAwdoWUs9SgQSx3OUow--~B/aD03MjA7dz0xMjgwO3NtPTE7YXBwaWQ9eXRhY2h5b24-/https://media.zenfs.com/pt-br/canal_tech_990/f3623f01efc419093ebc20a3c7b47058\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.jmonline.com.br/novo/?noticias,5,POL%C3%83%20CIA,151260\",\r\n" +
    // " \"pageTitle\": \"Justiça Federal condena assaltantes de agência dos \\u003cb\\u003eCorreios\\u003c/b\\u003e em
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.jmonline.com.br/uploads/noticia/151260_1.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.oantagonista.com/brasil/bolsonaro-diz-que-privatizacao-dos-correios-nao-depende-dele/\",\r\n" +
    // " \"pageTitle\": \"Bolsonaro diz que privatização dos \\u003cb\\u003eCorreios\\u003c/b\\u003e não depende dele -
    // O ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.oantagonista.net/uploads/2019/06/29213734989002-5311235.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://pontalemfoco.com.br/acontece/programa-jovem-aprendiz-nos-correios-encerram-inscricoes-na-sexta-feira-15/\",\r\n"
    // +
    // " \"pageTitle\": \"Programa Jovem Aprendiz nos \\u003cb\\u003ecorreios\\u003c/b\\u003e encerram inscrições na
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://pontalemfoco.com.br/wp-content/uploads/2018/06/323183.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://104maisfm.com.br/2018/05/08/crise-interna-acende-no-governo-possibilidade-de-privatizar-os-correios/\",\r\n"
    // +
    // " \"pageTitle\": \"Crise interna acende no governo possibilidade de privatizar os ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://104maisfm.com.br/wp-content/uploads/2018/05/323183.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://ndmais.com.br/blogs-e-colunas/janine-alves/correios-estao-com-vagas-de-estagio-de-nivel-superior-na-diretoria-regional-de-sc/\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e estão com vagas de estágio de nível superior na
    // Diretoria ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://static.ndonline.com.br/2017/04/b2708673efd68531b69d1db217a42f9dda3a504f.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://grnews.com.br/09082019/para-de-minas/voluntarios-da-brigada-1-estao-capacitados-para-combater-incendios-florestais-em-para-de-minas\",\r\n"
    // +
    // " \"pageTitle\": \"Voluntários da Brigada 1 estão capacitados para combater ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i2.wp.com/grnews.com.br/wp-content/uploads/2017/08/correios.jpg?resize=455%2C250&ssl=1\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://www.fmterra.com.br/noticias/2019/08/21/governo-anuncia-privatizacao-dos-correios-nesta-qu/\",\r\n" +
    // " \"pageTitle\": \"Governo anuncia privatização dos \\u003cb\\u003eCorreios\\u003c/b\\u003e nesta quarta - Rádio
    // FM ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.fmterra.com.br/media/arquivos/noticia/47629282642_032ea8ae72_z.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.causaoperaria.org.br/depois-de-fechar-as-agencias-golpistas-dos-correios-vao-vender-produtos-em-lojas-terceirizadas/?fb_comment_id=2165494340178356_2165681906826266\",\r\n"
    // +
    // " \"pageTitle\": \"Depois de fechar as agências, golpistas dos \\u003cb\\u003eCorreios\\u003c/b\\u003e vão vender
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.causaoperaria.org.br/wp-content/uploads/2019/01/correios-privatizado-2-1024x585.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.bemparana.com.br/noticia/bolsonaro-autoriza-estudo-para-privatizacao-dos-correios?fbclid=IwAR1-2jq1qQ-Yv1OcYyBhjHcQB739a-cuasaSO1P_XshVeLPbNUOJvbtTX8U\",\r\n"
    // +
    // " \"pageTitle\": \"Bolsonaro autoriza estudo para privatização dos \\u003cb\\u003eCorreios\\u003c/b\\u003e - Bem
    // ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://uploads.bemparana.com.br/upload/image/noticia/noticia_643528_img1_correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.folhavitoria.com.br/economia/noticia/04/2019/bolsonaro-autoriza-estudo-para-privatizacao-dos-correios\",\r\n"
    // +
    // " \"pageTitle\": \"Bolsonaro autoriza estudo para privatização dos
    // \\u003cb\\u003eCorreios\\u003c/b\\u003e\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://assets.folhavitoria.com.br/images/d0e1fe80-20e0-0137-e556-6231c35b6685--minified.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.androidpit.com.br/correios-nota-fiscal-embalagem\",\r\n" +
    // " \"pageTitle\": \"Fique de olho: \\u003cb\\u003eCorreios\\u003c/b\\u003e vão exigir nota fiscal na parte externa
    // de ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://fscl01.fonpit.de/userfiles/6675138/image/29213734989002.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://fscl01.fonpit.de/userfiles/6675138/image/29213734989002-w810h462.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://nogueirense.com.br/voce-ja-teve-uma-entrega-atrasada-descubra-como-evitar-este-inconveniente\",\r\n" +
    // " \"pageTitle\": \"Você já teve uma entrega atrasada? Descubra como evitar este ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://s3.portalt5.com.br/imagens/correios-2.jpg?mtime=20171017070216\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://m.topmidianews.com.br/colunistas/post/reajuste-dos-correios-nao-deve-ultrapassar-8/54782/\",\r\n" +
    // " \"pageTitle\": \"Reajuste dos \\u003cb\\u003eCorreios\\u003c/b\\u003e não deve ultrapassar 8% - Portal TOP
    // Mídia ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.topmidianews.com.br/upload/dn_coluna_post/2018/04/323183.jpeg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://sitevitoria.com.br/?rest_route&paged=7\",\r\n" +
    // " \"pageTitle\": \"Notícias do Espírito Santo! | Página 7 - Site Vitória\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://sitevitoria.com.br/wp-content/uploads/2019/09/d0e1fe80-20e0-0137-e556-6231c35b6685-minified.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://canaltech.com.br/governo/privatizacao-dos-correios-e-descartada-pelo-futuro-ministro-do-mctic-128637/\",\r\n"
    // +
    // " \"pageTitle\": \"Privatização dos \\u003cb\\u003eCorreios\\u003c/b\\u003e é descartada pelo futuro ministro do
    // MCTIC\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://timeline.canaltech.com.br/311607.700/privatizacao-dos-correios-e-descartada-pelo-futuro-ministro-do-mctic-128637.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.androidpit.com.br/liminar-suspende-aumento-tarifas-correios\",\r\n" +
    // " \"pageTitle\": \"Liminar suspende aumento nas tarifas dos \\u003cb\\u003eCorreios\\u003c/b\\u003e para o
    // Mercado ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://fscl01.fonpit.de/userfiles/6675138/image/29213734989002.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://fscl01.fonpit.de/userfiles/6675138/image/29213734989002-w810h462.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://noticias.reclameaqui.com.br/noticias/correios-reclamacoes-de-atraso-na-entrega-aumentaram-28-em-2_3148/?fb_comment_id=1448411931935802_1448762291900766\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e: reclamações de atraso na entrega aumentaram 28% em
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://noticias.reclameaqui.com.br/uploads/403348113.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.opopularjm.com.br/correios-reajustam-preco-do-servico-de-despacho-postal/\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e reajustam preço do serviço de despacho postal | O
    // Popular\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.opopularjm.com.br/wp-content/uploads/2017/09/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"http://jornalentrevista.com.br/v4/servidores-municipais-que-trabalharao-na-sala-do-empreendedor-sao-capacitados/\",\r\n"
    // +
    // " \"pageTitle\": \"Servidores municipais que trabalharão na Sala do Empreendedor ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://jornalentrevista.com.br/v4/wp-content/uploads/2019/09/d0e1fe80-20e0-0137-e556-6231c35b6685-minified-1.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.oantagonista.com/tag/general-floriano-peixoto/\",\r\n" +
    // " \"pageTitle\": \"General Floriano Peixoto Tag - O Antagonista\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://cdn.oantagonista.net/uploads/2019/06/29213734989002-5311235.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.acheconcursos.com.br/noticia/iades-libera-gabarito-do-concurso-dos-correios-nesta-segunda-feira-5309\",\r\n"
    // +
    // " \"pageTitle\": \"IADES libera gabarito do concurso dos \\u003cb\\u003eCorreios\\u003c/b\\u003e; confira\",\r\n"
    // +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.acheconcursos.com.br/imagens/post/32772/correios.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://noticias.reclameaqui.com.br/noticias/correios-reclamacoes-de-atraso-na-entrega-aumentaram-28-em-2_3148/?fb_comment_id=1448411931935802_1448566781920317\",\r\n"
    // +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e: reclamações de atraso na entrega aumentaram 28% em
    // ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://noticias.reclameaqui.com.br/uploads/403348113.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.gazetaonline.com.br/noticias/policia/2018/05/agencia-dos-correios-de-jeronimo-monteiro-e-arrombada-1014130216.html\",\r\n"
    // +
    // " \"pageTitle\": \"Agência dos \\u003cb\\u003eCorreios\\u003c/b\\u003e de Jerônimo Monteiro é arrombada | A
    // Gazeta\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://midias.gazetaonline.com.br/_midias/jpg/2017/10/04/605x330/1_29213734989002-5311235.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://content-thumbnail.cxpublic.com/content/dominantthumbnail/28e64eb0cc946a42f54075a1f11b94859c9d1078.jpg?5af0a896\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.ibahia.com/detalhe/noticia/correios-lanca-aplicativo-que-agiliza-atendimento/\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e lança aplicativo que agiliza atendimento -
    // iBahia\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.ibahia.com/fileadmin/user_upload/ibahia/2018/junho/06/correios1.jpg?width=1200&enable=upscale\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://br.financas.yahoo.com/noticias/correios-bolsonaro-cede-e-pode-123300810.html\",\r\n" +
    // " \"pageTitle\": \"\\u003cb\\u003eCorreios\\u003c/b\\u003e | Bolsonaro cede e pode permitir privatização da
    // estatal\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://s.yimg.com/uu/api/res/1.2/Fa3YAwdoWUs9SgQSx3OUow--~B/aD03MjA7dz0xMjgwO3NtPTE7YXBwaWQ9eXRhY2h5b24-/https://media.zenfs.com/pt-br/canal_tech_990/f3623f01efc419093ebc20a3c7b47058\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://s.yimg.com/ny/api/res/1.2/2bqZP23INrIn5pDd.AgRBw--/YXBwaWQ9aGlnaGxhbmRlcjt3PTEyODA7aD03MjA-/https://s.yimg.com/uu/api/res/1.2/Fa3YAwdoWUs9SgQSx3OUow--~B/aD03MjA7dz0xMjgwO3NtPTE7YXBwaWQ9eXRhY2h5b24-/https://media.zenfs.com/pt-br/canal_tech_990/f3623f01efc419093ebc20a3c7b47058\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://www.gazetaonline.com.br/noticias/policia/2018/11/bandidos-arrombam-restaurante-e-mercadinho-para-invadir-correios-no-es-1014154858.html\",\r\n"
    // +
    // " \"pageTitle\": \"Bandidos arrombam restaurante e mercadinho para invadir ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://midias.gazetaonline.com.br/_midias/jpg/2017/10/04/29213734989002-5311235.jpg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://canaltech.com.br/video/canaltech-news/novos-iphones-11-vs-4g-do-brasil-ct-news-11919/\",\r\n" +
    // " \"pageTitle\": \"Novos iPhones 11 vs 4G do Brasil [CT News] - Vídeos - Canaltech\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://timeline.canaltech.com.br/333499.400/sem-acordo-funcionarios-dos-correios-entram-em-greve-em-todo-o-brasil.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://canaltech.com.br/video/canaltech-news/samsung-adia-lancamento-do-galaxy-fold-moto-z4-com-camera-de-48mp-e-ct-news-11477/\",\r\n"
    // +
    // " \"pageTitle\": \"Samsung adia lançamento do Galaxy Fold; Moto Z4 com câmera de ...\",\r\n" +
    // " \"partialMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://timeline.canaltech.com.br/321123.400/correios-bolsonaro-cede-e-pode-permitir-privatizacao-da-estatal.jpg\"\r\n"
    // +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://imediatoonline.com/pais/bolsonaro-diz-que-amazonia-e-coracao-do-brasil-e-parabeniza-manaus-pelos-seus-350-anos/\",\r\n"
    // +
    // " \"pageTitle\": \"Bolsonaro diz que &#39;Amazônia é coração do Brasil&#39; e parabeniza ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://imediatoonline.com/wp-content/uploads/2019/05/download.jpeg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://imediatoonline.com/pais/pirulito-envenenado-morre-adolescente-que-teria-comido-o-bombom-dado-por-desconhecida/\",\r\n"
    // +
    // " \"pageTitle\": \"Pirulito envenenado? Morre adolescente que teria comido o ...\",\r\n" +
    // " \"fullMatchingImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://imediatoonline.com/wp-content/uploads/2019/05/download.jpeg\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " }\r\n" +
    // " ],\r\n" +
    // " \"visuallySimilarImages\": [\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://static.ndonline.com.br/2018/03/cropped/9c795a7a7e23d3e97f683791b09469a5e602cbd0.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://img.sbtinterior.com/trabalhadores-dos-correios-entram-em-greve-amanha-em-todo-o-brasil-1520791679-7.jpg\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\":
    // \"https://i1.wp.com/portaltailandia.com/wp-content/uploads/2018/03/Correios-entram-em-greve-2018.jpg?resize=350%2C200&ssl=1\"\r\n"
    // +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://www.guiamedianeira.com.br/img/noticias/2018/03/ex_13032018082537465623235796.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://radioculturadonordeste.com.br/wp-content/uploads/2018/08/2-correios.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"https://www.metro1.com.br/fotos/noticias/42952/IMAGEM_NOTICIA_3.jpg?v=f46b7eaed466297\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://agenciaal.alesc.sc.gov.br/images/uploads/fotonoticia/greve_correios.jpg\"\r\n" +
    // " },\r\n" +
    // " {\r\n" +
    // " \"url\": \"http://agenciagbc.com/wp-content/uploads/2018/03/Greve-Correios-800x445.jpg\"\r\n" +
    // " }\r\n" +
    // " ],\r\n" +
    // " \"bestGuessLabels\": [\r\n" +
    // " {\r\n" +
    // " \"label\": \"greve correios\",\r\n" +
    // " \"languageCode\": \"pt\"\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // " }\r\n" +
    // " }\r\n" +
    // " ]\r\n" +
    // "}\r\n" +
    // "";
    // }

    private static List<PageWithMatchingImagesDTO> convertJsonToPagesWithMatchingImages(String json) throws ParseException {
        Gson gson = new Gson();

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(json);
        JSONArray response = (JSONArray) jsonObject.get("responses");
        JSONObject responseFisrtResult = (JSONObject) response.get(0);
        JSONObject webDetection = (JSONObject) responseFisrtResult.get("webDetection");
        JSONArray pagesWithMatchingImagesObj = (JSONArray) webDetection.get("pagesWithMatchingImages");

        return Arrays.asList(gson.fromJson(pagesWithMatchingImagesObj.toString(), PageWithMatchingImagesDTO[].class));
    }

}
