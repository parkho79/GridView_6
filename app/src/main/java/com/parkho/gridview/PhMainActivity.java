package com.parkho.gridview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.GridViewWithHeaderAndFooter;

public class PhMainActivity extends AppCompatActivity {

    // List item
    private List<PhGridItem> mItemList;

    // Grid view
    private GridViewWithHeaderAndFooter mGridView;

    // GridView adapter
    private PhGridArrayAdapter mGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Grid 설정
        bindGrid();

        // 삽입 설정
        bindInsert();

        // 수정 설정
        bindModify();

        // 삭제 설정
        bindDelete();
    }

    /**
     * Grid 설정
     */
    private void bindGrid() {
        // Grid item 생성
        mItemList = new ArrayList<>();
        mItemList.add(new PhGridItem(R.drawable.emoji_u1f600, "emoji_u1f600"));
        mItemList.add(new PhGridItem(R.drawable.emoji_u1f601, "emoji_u1f601"));
        mItemList.add(new PhGridItem(R.drawable.emoji_u1f602, "emoji_u1f602"));
        mItemList.add(new PhGridItem(R.drawable.emoji_u1f603, "emoji_u1f603"));
        mItemList.add(new PhGridItem(R.drawable.emoji_u1f604, "emoji_u1f604"));

        mGridView = (GridViewWithHeaderAndFooter) findViewById(R.id.gridview);

        // Header 추가
        final View header = getLayoutInflater().inflate(R.layout.content_list_header, null, false) ;
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PhMainActivity.this, "The header is selected", Toast.LENGTH_SHORT).show();
            }
        });
        mGridView.addHeaderView(header);

        // Footer 추가
        final View footer = getLayoutInflater().inflate(R.layout.content_list_footer, null, false) ;
        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PhMainActivity.this, "The footer is selected", Toast.LENGTH_SHORT).show();
            }
        });
        mGridView.addFooterView(footer);

        // Grid adapter
        mGridAdapter = new PhGridArrayAdapter(this, mItemList);
        mGridView.setAdapter(mGridAdapter);

        // Grid item click event 처리
        mGridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {
                final PhGridItem item = (PhGridItem) mGridAdapter.getItem(a_position);
                Toast.makeText(PhMainActivity.this, item.getName() + " selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 삽입 설정
     */
    private void bindInsert() {
        findViewById(R.id.btn_insert).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Item 추가
                mItemList.add(new PhGridItem(R.drawable.emoji_u1f605, "emoji_u1f605 " + mItemList.size()));

                // Grid list 반영
                mGridAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 수정 설정
     */
    private void bindModify() {
        findViewById(R.id.btn_modify).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 'grid-view-with-header-footer' 를 사용할 경우 header & footer count 를 빼야한다.
                final int position = mGridView.getCheckedItemPosition() - (mGridView.getHeaderViewCount() + mGridView.getFooterViewCount());
                if (position == -1) {
                    Toast.makeText(PhMainActivity.this, R.string.err_no_selected_item, Toast.LENGTH_SHORT).show();
                    return;
                }

                // Grid item 수정
                PhGridItem gridItem = mItemList.get(position);
                gridItem.setName(gridItem.getName() + " is modified");

                // Grid list 반영
                mGridAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 삭제 설정
     */
    private void bindDelete() {
        findViewById(R.id.btn_delete).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 'grid-view-with-header-footer' 를 사용할 경우 header & footer count 를 빼야한다.
                final int position = mGridView.getCheckedItemPosition() - (mGridView.getHeaderViewCount() + mGridView.getFooterViewCount());
                if (position == -1) {
                    Toast.makeText(PhMainActivity.this, R.string.err_no_selected_item, Toast.LENGTH_SHORT).show();
                    return;
                }

                // 선택한 grid item 삭제
                mItemList.remove(position);

                // 선택 항목 초기화
                mGridView.setItemChecked(-1, true);

                // Grid list 반영
                mGridAdapter.notifyDataSetChanged();
            }
        });
    }
}