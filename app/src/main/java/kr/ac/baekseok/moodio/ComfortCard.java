package kr.ac.baekseok.moodio;

public class ComfortCard {
        private int imageResId;
        private String cardType;
        private String text;

        public ComfortCard(int imageResId, String cardType, String text) {
            this.imageResId = imageResId;
            this.cardType = cardType;
            this.text = text;
        }

        public int getImageResId() {
            return imageResId;
        }

        public String getCardType() {
            return cardType;
        }

        public String getText() {
            return text;
        }
}
