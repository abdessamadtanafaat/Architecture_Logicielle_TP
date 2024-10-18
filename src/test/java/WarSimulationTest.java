import org.example.Builder.KingDomBuilder;
import org.example.Builder.WarBuilder;
import org.example.Model.KingDom;
import org.example.Model.War;
import org.junit.Assert;
import org.junit.Test;

public class WarSimulationTest {

    @Test
  public void showKingDom()
  {
    KingDom kingdom = new KingDomBuilder().addKing("Youness")
        .addCountry("France", "20", "100", "50", "200", "100", "100")
                // (name, nbrOfSoldiersInCity1,
                // nbrOfCitizenInCity1, .....)
        .addCountry("Spain", "30", "200", "40", "300")
        .build();

    Assert.assertEquals("Youness:|F:<Fc1:20-100,Fc2:50-200,Fc3:100-100>, S:<Sc1:30-200,Sc2:40-300>|",
            kingdom.report());
    // NF : the first Lettre of the king's name + the first lettre of the
    // CountryName
    // Fc1 : the presentation of the first city in France
  }

  /************
   * every kingDom has a power it's the sum of soldiers power on all his kingdoms
   *************/

    @Test
    public void showKingDomPower()
    {
        KingDom kingdom = new KingDomBuilder().addKing("Idriss")
                .addCountry("France", "20", "100", "50", "200", "100", "100")
                .addCountry("Spain", "30", "200", "40", "300")
                .build();
        Assert.assertEquals(240, kingdom.currentPower());
    }

  /************
     * a kingDom have soldiers on edges of each country to protect or attack an
     * other kingdoms
     *************/

    @Test
    public void aKingDomHaveSoldiersOnEdges()
    {
        KingDom kingdom1 = new KingDomBuilder().addKing("Idriss")
                .addCountry("France", "20", "100", "50", "200", "100", "100")
                .addCountry("Spain", "30", "200", "40", "300")
                .addSoldiersOnEdges("500")
                .build();

        KingDom kingdom2 = new KingDomBuilder().addKing("MOHA")
                .addCountry("USA", "500", "1000", "400", "500", "200", "300", "2000", "300")
                .build();

        Assert.assertEquals("Idriss:|F:<Fc1:20-100,Fc2:50-200,Fc3:100-100>-500, S:<Sc1:30-200,Sc2:40-300>-500|",
                kingdom1.report());
        Assert.assertEquals("MOHA:|U:<Uc1:500-1000,Uc2:400-500,Uc3:200-300,Uc4:2000-300>|", kingdom2.report());

        Assert.assertEquals(240, kingdom1.currentPower());
        Assert.assertEquals(3100, kingdom2.currentPower());

    }

    @Test
    public void aKingDomCanPrepareAnAttackAnOther()
    {
        KingDom kingdom1 = new KingDomBuilder().addKing("Idriss")
                .addCountry("France", "20", "100", "50", "200", "100", "100")
                .addCountry("Spain", "30", "200", "40", "300")
                .addSoldiersOnEdges("500")
                .build();

        KingDom kingdom2 = new KingDomBuilder().addKing("MOHA")
                .addCountry("USA", "30", "200", "40", "300")
                .addSoldiersOnEdges("200")
                .build();

        Assert.assertEquals(240, kingdom1.currentPower());
        Assert.assertEquals(70, kingdom2.currentPower());

        War war = new WarBuilder().addKingDom(kingdom1)
                .addKingDom(kingdom2)
                .addMap("France:100:Spain,France:1000:USA,Spain:1500:USA")
                .build();

        // the kingdoms which has more power prepare Attack on the nearest kingdoms
        // when a kingdoms prepare an attack he moves 50% of his army on each city to
        // the country's edge which is the nearest to the other kingdoms
        war.prepareAttack();

        Assert.assertEquals("Idriss:|F:<Fc1:20-100,Fc2:50-200,Fc3:100-100>-535, S:<Sc1:15-200,Sc2:20-300>-500|",
                kingdom1.report());
        Assert.assertEquals("MOHA:|U:<Uc1:30-200,Uc2:40-300>-200|", kingdom2.report());
        Assert.assertEquals(205, kingdom1.currentPower());
        Assert.assertEquals(70, kingdom2.currentPower());
    }

/*
    @Test
    public void aKingDomCanAttackAttackAnOther()
    {
        KingDom kingdom1 = new KingDomBuilder().addKing("Idriss")
                .addCountry("France", "20", "100", "50", "200", "100", "100")
                .addCountry("Spain", "30", "200", "40", "300")
                .addSoldiersOnEdges("500")
                .build();

        KingDom kingdom2 = new KingDomBuilder().addKing("MOHA")
                .addCountry("USA", "30", "200", "40", "300")
                .addSoldiersOnEdges("200")
                .build();

        Assert.assertEquals(240, kingdom1.currentPower());
        Assert.assertEquals(70, kingdom2.currentPower());

        War war = new WarBuilder().addKingDom(kingdom1)
                .addKingDom(kingdom2)
                .addMap("France:100:Spain,France:1000:USA,Spain:1500:USA")
                .build();

        // the kingdoms which has more power prepare Attack on the nearest kingdoms
        // when a kingdoms prepare an attack he moves 50% of his army on each city to
        // the country's edge which is the nearest to the other kingdoms
        war.prepareAttack();

        Assert.assertEquals("Idriss:|F:<Fc1:20-100,Fc2:50-200,Fc3:100-100>-535, S:<Sc1:15-200,Sc2:20-300>-500|",
                kingdom1.report());
        Assert.assertEquals("MOHA:|U:<Uc1:30-200,Uc2:40-300>-200|", kingdom2.report());
        Assert.assertEquals(205, kingdom1.currentPower());
        Assert.assertEquals(70, kingdom2.currentPower());

        // the kingdoms attacks the soldiers on edge of the nearest country
      war.start();

        Assert.assertEquals("Idriss:|F:<Fc1:20-100,Fc2:50-200,Fc3:100-100>-335, S:<Sc1:15-200,Sc2:20-300>-500|",
                kingdom1.report());
        Assert.assertEquals("MOHA:|U:<Uc1:30-200,Uc2:40-300>|", kingdom2.report());
        Assert.assertEquals(205, kingdom1.currentPower());
        Assert.assertEquals(70, kingdom2.currentPower());

        //after an attack the soldiers and citizens changes their mood depending on
        Assert.assertEquals("GOOD", kingdom1.peopleMood());
        Assert.assertEquals("BAD", kingdom2.peopleMood());
    }

 */
}