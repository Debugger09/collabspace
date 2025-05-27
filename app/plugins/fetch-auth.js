export default defineNuxtPlugin(() => {
  const originalFetch = globalThis.$fetch || $fetch
  globalThis.$fetch = (...args) => {
    const [request, options = {}] = args
    const token = useCookie('auth_token').value
    options.headers = options.headers || {}
    if (token) {
      options.headers.Authorization = `Bearer ${token}`
    }
    return originalFetch(request, options)
  }
})