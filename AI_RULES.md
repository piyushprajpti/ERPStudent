# ERP Student Project Developer Guidelines

## Architecture & Structure
- **Screen Pattern:** Every screen must consist of four files: `[Name]Screen.kt`, `[Name]ViewModel.kt`, `[Name]State.kt`, and `[Name]Event.kt`.
- **Clean Code:** Keep the main `Screen.kt` file clean. Move UI components into a `components` sub-folder within the feature module (e.g., `feature/home/components/`).
- **ViewModel Data:** Always populate ViewModels with extensive dummy data that covers all possible states and edge cases for testing.

## UI & Styling Standards
- **Theme:** Always use `CustomTheme.colors` from `com.piyushprajpti.college_erp_system.theme`.
- **Colors:** NEVER use hardcoded `Color(0xFF...)` values. Use the theme colors.
- **TopBar:** Always use the project's custom `TopBar` component.
- **No Scaffold:** Do not use the material `Scaffold` component. Use custom layouts (Column/Box) with proper system bar padding.
- **Scrollability:** Every page must be scrollable (usually via `LazyColumn` or `Modifier.verticalScroll`).
- **Padding:** Use a default corner/screen padding of `15.dp`.
- **Typography:** Keep font sizes professional and normal. Avoid excessively large text. **NEVER use `MaterialTheme.typography` styles anywhere.**
- **Rounding:** Use consistent corner rounding (e.g., `RoundedCornerShape(15.dp)` or as defined in the theme).

## State Management
- **Lifecycle:** Use `collectAsStateWithLifecycle()` to collect state in Composables.
- **Template Helpers:** Always include the standard project helpers. **Place them at the top of the Composable, after all variable declarations but BEFORE the start of the UI layout (e.g., before the main Column or Row).**
    - `LoadingWidget(viewModel.loadingHelper.state.value)`
    - `AlertDialog(viewModel.messageHelper)`
    - `ToastWidget(viewModel.messageHelper)`

## Best Practices
- Use `sealed interface` for Events.
- Use `data class` for State with default values.
- Use `Koin` for ViewModel injection.
