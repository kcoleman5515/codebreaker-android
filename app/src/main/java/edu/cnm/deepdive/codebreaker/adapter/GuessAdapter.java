package edu.cnm.deepdive.codebreaker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.codebreaker.R;
import edu.cnm.deepdive.codebreaker.adapter.GuessAdapter.Holder;
import edu.cnm.deepdive.codebreaker.databinding.ItemGuessBinding;
import edu.cnm.deepdive.codebreaker.model.entity.Guess;
import edu.cnm.deepdive.codebreaker.model.pojo.GameWithGuesses;
import java.util.Map;

public class GuessAdapter extends RecyclerView.Adapter<Holder> {

  // TODO: Add fields for context, list of guesses, etc.
  private final LayoutInflater inflater;
  private final Map<Character, Integer> colorValueMap;
  private final Map<Character, String> colorLabelMap;
  private final GameWithGuesses game;

  public GuessAdapter(Context context,
      Map<Character, Integer> colorValueMap,
      Map<Character, String> colorLabelMap, GameWithGuesses game) {
    inflater = LayoutInflater.from(context);
    this.colorValueMap = colorValueMap;
    this.colorLabelMap = colorLabelMap;
    this.game = game;
  }
  // Above; Defined Constructor with parameters to initialize context, list of guesses, etc.

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemGuessBinding binding = ItemGuessBinding.inflate(inflater, parent, false);
    //  Inflate layout for guess item, and pass resulting binder object to Holder constructor.
    return new Holder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    // This method returns the number of guesses in the list.
    return game.getGuesses().size();
  }

  class Holder extends RecyclerView.ViewHolder {

    // TODO: Add fields for binder object, etc.
    private final ItemGuessBinding binding;

    public Holder(@NonNull ItemGuessBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    @SuppressWarnings("ConstantConditions")
    private void bind(int position) {
      // TODO: Populate binder object with content from model object identified by position.
      Guess guess = game.getGuesses().get(position);
      binding.guessNumber.setText(String.valueOf(position + 1));
      binding.exactMatches.setText(String.valueOf(guess.getExactMatches()));
      binding.nearMatches.setText(String.valueOf(guess.getNearMatches()));
      binding.swatchContainer.removeAllViews();
      for (char guessCharacter : guess.getText().toCharArray()) {
        ImageView view =
            (ImageView) inflater.inflate(R.layout.character_swatch, binding.swatchContainer, false);
        @ColorInt int backgroundColor = colorValueMap.get(guessCharacter);
        String colorLabel = colorLabelMap.get(guessCharacter);
        view.setBackgroundColor(backgroundColor);
        view.setContentDescription(colorLabel);
        binding.swatchContainer.addView(view);

      }
    }

  }

}
