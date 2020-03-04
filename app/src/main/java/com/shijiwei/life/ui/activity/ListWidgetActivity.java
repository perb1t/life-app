package com.shijiwei.life.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shijiwei.life.R;
import com.shijiwei.life.data.model.User;
import com.shijiwei.life.ui.adapter.ContactsAdapter;
import com.shijiwei.life.ui.base.BaseActivity;
import com.shijiwei.life.widget.ContactsDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/** Created by shijiwei on 2020-02-28. @Desc: */
public class ListWidgetActivity extends BaseActivity {

  @BindView(R.id.lv_contacts)
  RecyclerView lvContacts;

  private ContactsAdapter adapter;
  private List<User> users;

  @Override
  public int getLayoutResId() {
    return R.layout.activity_list_widget;
  }

  @Override
  public boolean showToolbar() {
    return true;
  }

  @Override
  public void onInit(@Nullable Bundle savedInstanceState) {
    users = new ArrayList<>();
    users.add(new User("阿里巴巴"));
    users.add(new User("阿伟"));
    users.add(new User("阿霞"));
    users.add(new User("李三"));
    users.add(new User("张思"));
    users.add(new User("王军"));
    users.add(new User("德玛西亚"));
    users.add(new User("汤姆"));

    for (int i = 0; i < 100; i++) {
      users.add(new User(" " + i));
    }
    adapter = new ContactsAdapter(users, this);
    lvContacts.setLayoutManager(new LinearLayoutManager(this));
    lvContacts.addItemDecoration(new ContactsDivider());
    lvContacts.setAdapter(adapter);
  }

  @Override
  public int getOrientation() {
    return 0;
  }
}
