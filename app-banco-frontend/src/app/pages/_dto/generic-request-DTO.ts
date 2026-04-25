/**
 * Interface GenericRequestDTO
 * @autor Juan Maigua <maiguarizocarlos@gmail.com>
 * @since 22/05/2026
 * @version 1.0
 */
export interface GenericRequestDTO<T> {
  userRequest: string;
  payload: T;
}
