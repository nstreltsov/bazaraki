package com.bazaraki.autotests.pages;

import com.bazaraki.autotests.elements.Button;
import com.bazaraki.autotests.elements.StaticText;
import com.bazaraki.autotests.entity.Offer;
import io.qameta.allure.Step;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Nikolay Streltsov on 16.05.2020
 */
public class ResultsPage extends AbstractPageObject{

    @FindBy(xpath = ".//*[contains(@class, 'list-announcement-block ')]//div[@class='shell shell--horizontal' and .//*[@class='photo-commodities']]")
    public List<ResultItem> items;

    @Step("Выбор самого старого предложения количеством фотографий больше 5 и минимальной ценой")
    public OfferPage searchItem(){
        AtomicInteger index = new AtomicInteger();
        int expectedPhotoCount = 5;
        Comparator<Offer> comparatorTime = Comparator.comparing(offer -> offer.getTimeLike());
        Comparator<Offer> comparatorPrice = Comparator.comparing(offer -> offer.getPrice());
        List<Offer> offers1 = items.stream()
                .map(resultItem -> (new Offer(index.getAndIncrement()////записываем все данные в промежуточный entity-объект, чтобы не обращаться несколько раз к фронту при сортировке
                        , stringToDate("dd.MM.yyyy", resultItem.timeLike.getText())//если с учетом минут, то "dd.MM.yyyy HH:mm"
                        , Double.valueOf(resultItem.price.getAttribute("content"))
                        , Integer.valueOf(resultItem.photoCount.getText())
                    )
                )
        ).filter(offer -> offer.getPhotoCount() >= expectedPhotoCount)//отбираем те, где фотографий больше 5
        .sorted(comparatorTime)//сортируем по дате
                .collect(Collectors.toList());
        Offer searchOffer = offers1.stream()
                .filter(offer -> offer.getTimeLike().compareTo(offers1.get(0).getTimeLike()) == 0)//отбираем все, у кого время как у первого элемента
                .sorted(comparatorPrice)//сортируем по стоимости
                .findFirst()//берем первый
                .orElse(null);
        items.get(searchOffer.getIndex()).name.click();
        return new OfferPage();

    }

    public boolean isLoaded() {
        return true;
    }

    private Date stringToDate(String format, String value){
        try {
            if (value.contains("Today")){
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Calendar cal = Calendar.getInstance();
                value = value.replace("Today", dateFormat.format(cal.getTime()));
            }else if(value.contains("Yesterday")){
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, -1);
                value = value.replace("Yesterday", dateFormat.format(cal.getTime()));
            }
            return new SimpleDateFormat(format).parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class ResultItem extends CollectionObject {

        public ResultItem(SearchContext searchContext){
            super(searchContext);
        }
        @FindBy(xpath = ".//a[@class='name  ']")
        public Button name;

        @FindBy(xpath = ".//*[@class='time-like']")
        public StaticText timeLike;

        @FindBy(xpath = ".//*[@itemprop='price']")
        public StaticText price;

        @FindBy(xpath = ".//*[@class='photo-commodities']/p")
        public StaticText photoCount;
    }
}
