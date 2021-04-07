<template>
  <div class="option"> 
    <ul>
      <li 
        v-for="option in options" 
        :key="option.name" 
        class="option_group"
      >
        <div class="option_key">{{ option.name }}</div>
        <div 
          v-for="attribute in option.attributesList" 
          :key="attribute"
          class="option_values" 
        >
        <label :for="attribute">
          <input 
            :id="attribute" 
            :value="attribute" 
            type="checkbox" 
            @click="changeOption(option.name, $event)"
          >
          {{attribute}}
        </label>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import * as _ from 'lodash';
import '../../assets/css/Items/Option.css';
import store from '../../store/store';

export default {
  name: 'Option',
  data() {
    return {
      selectedOptions: {},
    }
  },
  computed: {
    options() {
      return store.state.option.options;
    }
  },
  mounted() {
    this.setSelectedOptions();
    this.getItems({});
  },
  methods: {
    setSelectedOptions() {
      this.options.forEach(option => {
        this.selectedOptions[option.name] = []
      });
    },
    changeOption(optionName, event) {
      const attributeName = event.target.value;
      const targetIndex = this.selectedOptions[optionName].indexOf(attributeName);
      (~targetIndex) 
        ? this.selectedOptions[optionName].splice(targetIndex, 1)
        : this.selectedOptions[optionName].push(attributeName);
      this.getItems(this.selectedOptions);
    },
    changeString(selectedOptions) {
      const copySelectedOptions = _.cloneDeep(selectedOptions);
      for(const [option, optionName] of Object.entries(selectedOptions)) {
        if (optionName.length) {
          copySelectedOptions[option] = optionName.join(',');
        } else {
          delete copySelectedOptions[option];
        }
      }
      return copySelectedOptions;
    },
    getItems(selectedOptions) {
      this.$emit('getItems', this.changeString(selectedOptions));
    },
  },
}
</script>

<style scoped>
</style>