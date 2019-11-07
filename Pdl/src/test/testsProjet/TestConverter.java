package testsProjet;

import model.Converter;
import model.Table;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;

public class TestConverter {

    Table tableHTML;
    Table tableWikitext;
    Converter converter;

    @Before
    public void setUp () {
        //Init Page1
        String [] ligne1 = {"Aspect","Esperanto","Interlangua"};
        String [] ligne2 = {"Type","Designed","Naturalistic"};
        String [] ligne3 = {"Gender","masculine","thirdPerson"};

        HashMap<Integer,String[]> content = new HashMap<Integer, String []>();
        content.put(1,ligne1);
        content.put(2, ligne2);
        content.put(3,ligne3);

        tableHTML = new Table(content,"Test tableau","html",1);
        tableWikitext = new Table(content,"Test tableau","wikitext",1);
        converter = new Converter();
    }

    @Test
    public void testConverter () {
        Assert.assertTrue("Conversion HTML : Le fichier n'a pas été crée",converter.convertToCSV(tableHTML));
        Assert.assertTrue("Conversion Wikitext : Le fichier n'a pas été crée",converter.convertToCSV(tableWikitext));

        //Tester si le nom du fichier est correct + ajouter test intégration cf TestCSV.java
    }

    @AfterClass
    public void deleteFile () {
        String folderName = File.separator+"output"+File.separator+tableHTML.getExtractionType()+File.separator;
        File file = new File(System.getProperty("user.dir") +folderName + tableHTML.getTitle().trim() + "-" +tableHTML.getNumTable()+ ".csv");
        file.delete();


        folderName = File.separator+"output"+File.separator+tableWikitext.getExtractionType()+File.separator;
        file = new File(System.getProperty("user.dir") +folderName + tableWikitext.getTitle().trim() + "-" +tableWikitext.getNumTable()+ ".csv");
        file.delete();
    }

    //Tester ne nombre de virgule sans tenir compte des ","
}