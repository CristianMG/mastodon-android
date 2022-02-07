package org.joinmastodon.android.api.requests.accounts;

import com.google.gson.reflect.TypeToken;

import org.joinmastodon.android.api.MastodonAPIRequest;
import org.joinmastodon.android.model.Status;

import java.util.List;

import androidx.annotation.NonNull;

public class GetAccountStatuses extends MastodonAPIRequest<List<Status>>{
	public GetAccountStatuses(String id, String maxID, String minID, int limit, @NonNull Filter filter){
		super(HttpMethod.GET, "/accounts/"+id+"/statuses", new TypeToken<>(){});
		if(maxID!=null)
			addQueryParameter("max_id", maxID);
		if(minID!=null)
			addQueryParameter("min_id", minID);
		if(limit>0)
			addQueryParameter("limit", ""+limit);
		switch(filter){
			case DEFAULT -> addQueryParameter("exclude_replies", "true");
			case INCLUDE_REPLIES -> {}
			case MEDIA -> addQueryParameter("only_media", "true");
		}
	}

	public enum Filter{
		DEFAULT,
		INCLUDE_REPLIES,
		MEDIA
	}
}