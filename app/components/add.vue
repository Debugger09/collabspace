<!-- components/add.vue -->
<template>
  <Header>
    <div class="p-4">
      <!-- Titre + Bouton retour -->
      <div class="flex justify-between items-center mb-4">
        <h1 class="text-lg font-semibold text-blue-900">{{ title }}</h1>
        <NuxtLink
          :to="backUrl"
          class="flex items-center px-3 py-1.5 bg-gray-100 text-blue-800 rounded hover:bg-gray-200 transition text-sm"
        >
          <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
          </svg>
          Retour
        </NuxtLink>
      </div>

      <!-- Formulaire -->
      <div class="max-w-xl mx-auto bg-white shadow-sm rounded-lg p-4">

        <form @submit.prevent="onSubmit" class="space-y-4 text-sm">
          <slot />

          <div v-if="error" class="text-red-500 text-center">{{ error }}</div>
          <div v-if="success" class="text-green-600 text-center">{{ success }}</div>

          <div class="flex justify-end gap-2 pt-2">
            <button
              type="submit"
              class="bg-blue-600 text-white px-5 py-1.5 rounded hover:bg-blue-700 transition disabled:opacity-50"
              :disabled="loading"
            >
              {{ submitLabel }}
            </button>
            <NuxtLink :to="backUrl" class="text-blue-600 hover:underline py-1.5 px-3">Annuler</NuxtLink>
          </div>
        </form>
      </div>
    </div>
  </Header>
</template>

<script setup>
import Header from '~/components/header.vue'

defineProps({
  title: String,
  backUrl: String,
  submitLabel: {
    type: String,
    default: 'Ajouter',
  },
  error: String,
  success: String,
  loading: Boolean,
})

const emit = defineEmits(['submit'])

const onSubmit = () => {
  emit('submit')
}
</script>
