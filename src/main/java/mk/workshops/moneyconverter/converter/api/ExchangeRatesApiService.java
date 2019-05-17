package mk.workshops.moneyconverter.converter.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ExchangeRatesApiService {
    @GET("latest")
    Call<Exchange> getRatios(@Query("base") String base, @Query("symbols") String symbols);
}
