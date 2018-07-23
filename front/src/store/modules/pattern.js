class PatternElement {
  constructor(type) {
    this.descripton = "Описание";
    this.id = 0;
    this.layoutX = 0;
    this.layoutY = 0;
    this.name = "Ваше название";
    this.required = false;
    this.selectableValue = [];
    this.type = type
  }
}

export default {
  namespaced: true,
  getters: {
    getPatternTextElement: () => new PatternElement('TEXT'),
    getPatternComboboxElement: () => new PatternElement('COMBOBOX'),
    getPatternCheckboxElement: () => new PatternElement('CHECKBOX'),
    getPatternRadioButtonsElement: () => new PatternElement('RADIOBUTTON'),
    getPatternMultiSelectElement: () => new PatternElement('MULTISELECT'),
  }
}