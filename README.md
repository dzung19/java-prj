# java-prj
Bài toán Max Flow là một bài toán tối ưu đồ thị mà ta phải đi tìm luồng lớn nhất có thể được gửi qua một mạng các kênh, đường ống mà có sức chứa giới hạn.
Trong bài toán này thì ta sẽ sử dụng đồ thị có hướng có trọng số(Trong bài toán này thì ta sẽ gọi trọng số là sức chứa của đường đi) với đỉnh nguồn S và đỉnh đích T. Mục tiêu của bài toán này là tìm luồng lớn nhất có thể được gửi từ S đến T mà không vượt quá sức chứa của đường đi.

Thuật toán tham làm và Ford-Fulkerson để giải quyết bài toán  maxflow trong đồ thị.
File maxflow sử dụng thuật toán tham lam cơ bản.
File MaxFlowFordFulkerson sử dụng thuật toán Ford-Fulkerson với thuật toán bfs tìm đường đi nhỏ nhất